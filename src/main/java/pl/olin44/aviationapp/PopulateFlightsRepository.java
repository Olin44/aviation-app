package pl.olin44.aviationapp;

import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Component;
import pl.olin44.aviationapp.json_file_reading.FlightEntityFromJSON;
import pl.olin44.aviationapp.json_file_reading.JSONReader;
import pl.olin44.aviationapp.mappers.FlightsEntityMapper;
import pl.olin44.aviationapp.repository.FlightEntityRepository;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Component
public class PopulateFlightsRepository {
    private final String PATH_TO_JSON_REPOSITORY = "/flight_entity.json";
    private final FlightEntityRepository flightEntityRepository;
    private final FlightsEntityMapper flightsEntityMapper;

    public PopulateFlightsRepository(FlightEntityRepository flightEntityRepository, FlightsEntityMapper flightsEntityMapper) {
        this.flightEntityRepository = flightEntityRepository;
        this.flightsEntityMapper = flightsEntityMapper;
        populate();
    }

    private void populate() {
        JSONReader jsonReader = new JSONReader();
        Type type = new TypeToken<ArrayList<FlightEntityFromJSON>>() {
        }.getType();
        List<FlightEntityFromJSON> entitiesFromJson = jsonReader.read(PATH_TO_JSON_REPOSITORY, type);
        entitiesFromJson.stream()
                .map(flightsEntityMapper::flightEntityMapper)
                .forEach(flightEntityRepository::add);
    }

}