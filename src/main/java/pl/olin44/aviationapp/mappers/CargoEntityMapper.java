package pl.olin44.aviationapp.mappers;

import org.springframework.stereotype.Component;
import pl.olin44.aviationapp.domain.BaggageEntity;
import pl.olin44.aviationapp.domain.CargoEntity;
import pl.olin44.aviationapp.domain.FlightCargoEntity;
import pl.olin44.aviationapp.domain.WeightUnit;
import pl.olin44.aviationapp.json_file_reading.BaggageFromJson;
import pl.olin44.aviationapp.json_file_reading.CargoEntityFromJson;
import pl.olin44.aviationapp.json_file_reading.CargoFromJson;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CargoEntityMapper {
    public CargoEntity cargoEntityMapper(CargoFromJson baggageFromJson) {
        return CargoEntity.builder()
                .id(baggageFromJson.getId())
                .pieces(baggageFromJson.getPieces())
                .weight(baggageFromJson.getWeight())
                .weightUnit(WeightUnit.valueOf(baggageFromJson.getWeightUnit()))
                .build();
    }

    public List<CargoEntity> cargoEntityListMapper(List<CargoFromJson> cargoEntities) {
        return cargoEntities.stream()
                .map(this::cargoEntityMapper)
                .collect(Collectors.toList());
    }

    public FlightCargoEntity flightCargoEntityMapper(CargoEntityFromJson cargoEntityFromJson) {
        return FlightCargoEntity.builder()
                .flightId(cargoEntityFromJson.getFlightId())
                .baggageEntity(baggageEntityListMapper(cargoEntityFromJson.getBaggage()))
                .cargoEntities(cargoEntityListMapper(cargoEntityFromJson.getCargo()))
                .build();
    }

    public List<BaggageEntity> baggageEntityListMapper(List<BaggageFromJson> baggageFromJsons) {
        return baggageFromJsons.stream().map(this::baggageEntityMapper).collect(Collectors.toList());
    }

    public BaggageEntity baggageEntityMapper(BaggageFromJson baggageFromJson) {
        return BaggageEntity.builder()
                .id(baggageFromJson.getId())
                .pieces(baggageFromJson.getPieces())
                .weight(baggageFromJson.getWeight())
                .weightUnit(WeightUnit.valueOf(baggageFromJson.getWeightUnit()))
                .build();
    }
}
