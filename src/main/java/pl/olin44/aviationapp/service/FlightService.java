package pl.olin44.aviationapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.olin44.aviationapp.domain.BaggageEntity;
import pl.olin44.aviationapp.domain.Cargo;
import pl.olin44.aviationapp.domain.FlightEntity;
import pl.olin44.aviationapp.domain.IATACode;
import pl.olin44.aviationapp.payload.response.FlightCargoStatisticsByIATACodeResponse;
import pl.olin44.aviationapp.repository.CargoRepository;
import pl.olin44.aviationapp.repository.FlightEntityRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightService {
    private final CargoRepository cargoRepository;
    private final FlightEntityRepository flightEntityRepository;

    public FlightCargoStatisticsByIATACodeResponse getStatisticsByIATACode(String iataCodeAsString) {
        IATACode iataCode = new IATACode(iataCodeAsString);
        List<FlightEntity> arrivingFlightsByIATACode = flightEntityRepository.findArrivingFlightsByIATACode(iataCode);
        long arrivingFlight = arrivingFlightsByIATACode.size();
        List<FlightEntity> departingFlightsByIATACode = flightEntityRepository.findDepartingFlightsByIATACode(iataCode);
        long departingFlight = departingFlightsByIATACode.size();
        long piecesOfBaggageDeparting = getBaggageCount(departingFlightsByIATACode);
        long piecesOfBaggageArriving = getBaggageCount(arrivingFlightsByIATACode);
        return FlightCargoStatisticsByIATACodeResponse.builder()
                .piecesOfBaggageArriving(piecesOfBaggageArriving)
                .piecesOfBaggageDeparting(piecesOfBaggageDeparting)
                .numberOfArrivingFlights(arrivingFlight)
                .numberOfDepartingFlight(departingFlight)
                .build();
    }

    private long getBaggageCount(List<FlightEntity> flightEntities) {
        return flightEntities.stream()
                .map(x -> getBaggageByFlightId(x.getFlightId()))
                .flatMap(List::stream)
                .mapToLong(Cargo::getPieces)
                .sum();
    }

    private List<BaggageEntity> getBaggageByFlightId(int id) {
        return cargoRepository.findBaggageByFlightId(id);
    }

}
