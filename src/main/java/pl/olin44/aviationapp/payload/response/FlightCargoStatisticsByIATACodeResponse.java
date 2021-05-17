package pl.olin44.aviationapp.payload.response;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class FlightCargoStatisticsByIATACodeResponse {
    long numberOfDepartingFlight;
    long numberOfArrivingFlights;
    long piecesOfBaggageDeparting;
    long piecesOfBaggageArriving;
}
