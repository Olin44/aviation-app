package pl.olin44.aviationapp.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import pl.olin44.aviationapp.domain.FlightEntity;
import pl.olin44.aviationapp.domain.IATACode;
import pl.olin44.aviationapp.exceptions.EntityNotFoundException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class FlightEntityRepository {
    private final List<FlightEntity> flightEntities;

    public FlightEntityRepository() {
        flightEntities = new ArrayList<>();
    }

    public void add(FlightEntity flightEntity) {
        flightEntities.add(flightEntity);
    }

    public List<FlightEntity> findDepartingFlightsByIATACode(IATACode iataCode) {
        return flightEntities.stream()
                .filter(x -> x.getDepartureAirportIATACode().equals(iataCode))
                .collect(Collectors.toList());
    }

    public List<FlightEntity> findArrivingFlightsByIATACode(IATACode iataCode) {
        return flightEntities.stream()
                .filter(x -> x.getArrivalAirportIATACode().equals(iataCode))
                .collect(Collectors.toList());
    }


    public FlightEntity findByFlightNumberAndDate(int flightNumber, LocalDateTime date) {
        Optional<FlightEntity> flightEntityOptional = flightEntities.stream()
                .filter(getFlightEntityPredicate(flightNumber, date))
                .findFirst();
        if (flightEntityOptional.isEmpty()) {
            throw new EntityNotFoundException(String.format("Entity with flight number: %d not found", flightNumber));
        }
        return flightEntityOptional.get();
    }

    private Predicate<FlightEntity> getFlightEntityPredicate(int flightNumber, LocalDateTime date) {
        return x -> x.getFlightNumber() == flightNumber && x.getDepartureDate().equals(date);
    }
}
