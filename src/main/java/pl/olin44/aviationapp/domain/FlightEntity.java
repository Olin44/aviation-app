package pl.olin44.aviationapp.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class FlightEntity {
    private final int flightId;
    private final int flightNumber;
    private final IATACode departureAirportIATACode;
    private final IATACode arrivalAirportIATACode;
    private final LocalDateTime departureDate;

    public FlightEntity(int flightId, int flightNumber, IATACode departureAirportIATACode, IATACode arrivalAirportIATACode, LocalDateTime departureDate) {
        this.flightId = flightId;
        this.flightNumber = flightNumber;
        this.departureAirportIATACode = departureAirportIATACode;
        this.arrivalAirportIATACode = arrivalAirportIATACode;
        this.departureDate = departureDate;
    }

}
