package pl.olin44.aviationapp;

import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.Test;
import pl.olin44.aviationapp.json_file_reading.FlightEntityFromJSON;
import pl.olin44.aviationapp.json_file_reading.JSONReader;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class JSONReaderTests {
    @Test
    void shouldReadFlightEntityFromJson() {
//      Arrange
        JSONReader jsonReader = new JSONReader();
        Type type = new TypeToken<ArrayList<FlightEntityFromJSON>>() {
        }.getType();
        FlightEntityFromJSON flightEntityFromJSON = FlightEntityFromJSON.builder()
                .flightId(0)
                .flightNumber(8360)
                .arrivalAirportIATACode("KRK")
                .departureAirportIATACode("YYT")
                .departureDate("Tue May 04 2021 15:01:57 GMT+0000 (UTC)")
                .build();
//       Act
        List<FlightEntityFromJSON> flightEntityFromJSONList = jsonReader
                .read("/flight_entity_test_data.json", type);
//      Assert
        assertThat(flightEntityFromJSONList).isNotNull();
        assertThat(flightEntityFromJSONList).hasSize(1);
        assertThat(flightEntityFromJSONList).contains(flightEntityFromJSON);
    }

}
