package pl.olin44.aviationapp.json_file_reading;

import lombok.Value;

@Value
public class CargoFromJson {
    int id;
    double weight;
    long pieces;
    String weightUnit;
}
