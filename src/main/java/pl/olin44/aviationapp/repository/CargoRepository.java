package pl.olin44.aviationapp.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.olin44.aviationapp.domain.BaggageEntity;
import pl.olin44.aviationapp.domain.CargoEntity;
import pl.olin44.aviationapp.domain.FlightCargoEntity;
import pl.olin44.aviationapp.exceptions.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class CargoRepository {
    private final List<FlightCargoEntity> cargoEntities;

    public CargoRepository() {
        cargoEntities = new ArrayList<>();
    }

    public void add(FlightCargoEntity flightCargoEntity) {
        cargoEntities.add(flightCargoEntity);
    }

    public List<FlightCargoEntity> getAll() {
        return List.copyOf(cargoEntities);
    }

    public FlightCargoEntity findCargoEntity(int flightId) {
        return cargoEntities.stream()
                .filter(x -> x.getFlightId() == flightId)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Entity with flight id: %d not found", flightId)));
    }

    public List<BaggageEntity> findBaggageByFlightId(int flightId) {
        return findCargoEntity(flightId).getBaggageEntity();
    }

    public List<CargoEntity> findCargoByFlightId(int flightId) {
        return findCargoEntity(flightId).getCargoEntities();
    }
}
