package pl.olin44.aviationapp.json_file_reading;

import lombok.Value;

import java.util.List;

@Value
public class CargoEntityFromJson {
    int flightId;
    List<BaggageFromJson> baggage;
    List<CargoFromJson> cargo;
}
