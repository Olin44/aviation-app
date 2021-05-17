package pl.olin44.aviationapp.mappers;

import org.springframework.stereotype.Component;
import pl.olin44.aviationapp.domain.FlightEntity;
import pl.olin44.aviationapp.domain.IATACode;
import pl.olin44.aviationapp.json_file_reading.FlightEntityFromJSON;
import pl.olin44.aviationapp.utils.DateParser;

import java.time.LocalDateTime;
import java.util.Locale;

@Component
public class FlightsEntityMapper {
    public FlightEntity flightEntityMapper(FlightEntityFromJSON flightEntityFromJSON) {
        return FlightEntity.builder()
                .flightId(flightEntityFromJSON.getFlightId())
                .arrivalAirportIATACode(new IATACode(flightEntityFromJSON.getArrivalAirportIATACode()))
                .departureAirportIATACode(new IATACode(flightEntityFromJSON.getDepartureAirportIATACode()))
                .departureDate(parseDate(flightEntityFromJSON.getDepartureDate()))
                .flightNumber(flightEntityFromJSON.getFlightNumber())
                .build();
    }

    private LocalDateTime parseDate(String date) {
        DateParser dateParser = DateParser.builder()
                .locale(Locale.forLanguageTag("en-US"))
                .pattern("EEE MMM dd yyyy HH:mm:ss 'GMT'Z (z)")
                .build();
        return dateParser.parse(date);
    }
}
