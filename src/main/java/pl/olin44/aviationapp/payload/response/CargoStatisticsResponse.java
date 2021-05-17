package pl.olin44.aviationapp.payload.response;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
@Value
@Builder
public class CargoStatisticsResponse {
    TotalWeight totalWeight;
    BaggageWeight baggageWeight;
    CargoWeight cargoWeight;
}
