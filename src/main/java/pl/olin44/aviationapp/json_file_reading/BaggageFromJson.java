package pl.olin44.aviationapp.json_file_reading;

import lombok.Value;

@Value
public class BaggageFromJson {
    int id;
    double weight;
    int pieces;
    String weightUnit;
}
