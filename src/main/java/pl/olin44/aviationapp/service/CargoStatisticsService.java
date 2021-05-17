package pl.olin44.aviationapp.service;

import lombok.RequiredArgsConstructor;
import pl.olin44.aviationapp.domain.BaggageEntity;
import pl.olin44.aviationapp.domain.Cargo;
import pl.olin44.aviationapp.domain.CargoEntity;
import pl.olin44.aviationapp.domain.FlightEntity;
import pl.olin44.aviationapp.payload.response.BaggageWeight;
import pl.olin44.aviationapp.payload.response.CargoStatisticsResponse;
import pl.olin44.aviationapp.payload.response.CargoWeight;
import pl.olin44.aviationapp.payload.response.TotalWeight;
import pl.olin44.aviationapp.repository.CargoRepository;
import pl.olin44.aviationapp.repository.FlightEntityRepository;
import pl.olin44.aviationapp.utils.DateParser;
import pl.olin44.aviationapp.utils.WeightCalculation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class CargoStatisticsService {
    private final FlightEntityRepository flightEntityRepository;
    private final CargoRepository cargoRepository;

    public CargoStatisticsResponse cargoStatistics(int flightNumber, String date) {
        LocalDateTime localDateTime = convertStringToLocalDate(date);
        int flightId = getFlightId(flightNumber, localDateTime);
        List<BaggageEntity> baggageEntityList = cargoRepository.findBaggageByFlightId(flightId);
        List<CargoEntity> cargoEntities = cargoRepository.findCargoByFlightId(flightId);
        return createCargoStatisticsResponse(baggageEntityList, cargoEntities);
    }

    private CargoStatisticsResponse createCargoStatisticsResponse(List<BaggageEntity> baggageEntityList, List<CargoEntity> cargoEntities) {
        TotalWeight baggageTotalWeight = createTotalWeight(baggageEntityList);
        TotalWeight cargoTotalWeight = createTotalWeight(cargoEntities);
        BaggageWeight baggageWeight = BaggageWeight.builder()
                .kg(baggageTotalWeight.getKg())
                .lb(baggageTotalWeight.getLb())
                .build();
        CargoWeight cargoWeight = CargoWeight.builder()
                .kg(cargoTotalWeight.getKg())
                .lb(cargoTotalWeight.getLb())
                .build();
        TotalWeight totalWeight = sum(cargoWeight, baggageWeight);
        return CargoStatisticsResponse.builder().baggageWeight(baggageWeight).cargoWeight(cargoWeight).totalWeight(totalWeight).build();
    }

    private int getFlightId(int flightNumber, LocalDateTime localDateTime) {
        FlightEntity flightEntity = flightEntityRepository.findByFlightNumberAndDate(flightNumber, localDateTime);
        return flightEntity.getFlightId();
    }

    private LocalDateTime convertStringToLocalDate(String date) {
        DateParser parser = DateParser.builder()
                .locale(Locale.ENGLISH)
                .pattern(("EEE MMM dd yyyy HH:mm:ss 'GMT'Z (z)"))
                .build();
        return parser.parse(date);
    }

    private <T extends TotalWeight> TotalWeight sum(T totalWeight1, T totalWeight2) {
        double weightInKg = 0;
        double weightInLb = 0;
        weightInKg = totalWeight1.getKg() + totalWeight2.getKg();
        weightInLb = totalWeight1.getLb() + totalWeight2.getLb();
        return T.builder().kg(weightInKg).lb(weightInLb).build();
    }

    private <T extends Cargo> TotalWeight createTotalWeight(List<T> baggageEntityList) {
        double weightInKg = 0;
        double weightInLb = 0;
        for (Cargo baggageEntity : baggageEntityList) {
            switch (baggageEntity.getWeightUnit()) {
                case kg:
                    weightInKg += baggageEntity.getWeight();
                case lb:
                    weightInLb += baggageEntity.getWeight();
            }
        }
        double totalWeightInLB = WeightCalculation.kgToLb(weightInKg) + weightInLb;
        double totalWeightInKg = WeightCalculation.lbToKg(weightInLb) + weightInKg;
        return TotalWeight.builder()
                .kg(totalWeightInKg)
                .lb(totalWeightInLB).build();
    }
}
