package pl.olin44.aviationapp.json_file_reading;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class FlightEntityFromJSON {
    int flightId;
    int flightNumber;
    String departureAirportIATACode;
    String arrivalAirportIATACode;
    String departureDate;
}
