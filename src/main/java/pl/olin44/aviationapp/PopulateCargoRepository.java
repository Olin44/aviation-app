package pl.olin44.aviationapp;

import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Component;
import pl.olin44.aviationapp.json_file_reading.CargoEntityFromJson;
import pl.olin44.aviationapp.json_file_reading.JSONReader;
import pl.olin44.aviationapp.mappers.CargoEntityMapper;
import pl.olin44.aviationapp.repository.CargoRepository;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Component
public class PopulateCargoRepository {
    private final String PATH_TO_JSON_REPOSITORY = "/cargo_entity.json";
    private final CargoRepository cargoRepository;
    private final CargoEntityMapper cargoEntityMapper;

    public PopulateCargoRepository(CargoRepository cargoRepository, CargoEntityMapper cargoEntityMapper) {
        this.cargoRepository = cargoRepository;
        this.cargoEntityMapper = cargoEntityMapper;
        populate();
    }

    private void populate() {
        JSONReader jsonReader = new JSONReader();
        Type type = new TypeToken<ArrayList<CargoEntityFromJson>>() {
        }.getType();
        List<CargoEntityFromJson> entitiesFromJson = jsonReader.read(PATH_TO_JSON_REPOSITORY, type);
        entitiesFromJson.stream().map(cargoEntityMapper::flightCargoEntityMapper).forEach(cargoRepository::add);
    }

}
