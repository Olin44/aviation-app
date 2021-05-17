package pl.olin44.aviationapp.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

@RequiredArgsConstructor
@Getter
@Builder
@ToString
public class FlightCargoEntity {
    private final int flightId;
    private final List<BaggageEntity> baggageEntity;
    private final List<CargoEntity> cargoEntities;
}
