package pl.olin44.aviationapp;

import org.junit.jupiter.api.Test;
import pl.olin44.aviationapp.domain.BaggageEntity;
import pl.olin44.aviationapp.domain.CargoEntity;
import pl.olin44.aviationapp.domain.FlightCargoEntity;
import pl.olin44.aviationapp.domain.WeightUnit;
import pl.olin44.aviationapp.repository.CargoRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CargoRepositoryTests {
    @Test
    void shouldAddCargoToCargoRepository() {
//       Arrange
        CargoRepository cargoRepository = new CargoRepository();
        CargoEntity cargoEntity = CargoEntity.builder()
                .weightUnit(WeightUnit.kg)
                .weight(200)
                .pieces(200)
                .id(1)
                .build();
        BaggageEntity baggageEntity = BaggageEntity.builder()
                .id(1)
                .pieces(100)
                .weightUnit(WeightUnit.kg)
                .weight(200).build();
        FlightCargoEntity flightCargoEntity = FlightCargoEntity.builder()
                .flightId(1)
                .baggageEntity(List.of(baggageEntity))
                .cargoEntities(List.of(cargoEntity))
                .build();
//      Act
        cargoRepository.add(flightCargoEntity);
        List<FlightCargoEntity> flightCargoEntities = cargoRepository.getAll();
//      Assert
        assertThat(flightCargoEntities).isNotNull();
        assertThat(flightCargoEntities).hasSize(1);
        assertThat(flightCargoEntities).contains(flightCargoEntity);
    }
}
