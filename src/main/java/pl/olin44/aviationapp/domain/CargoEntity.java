package pl.olin44.aviationapp.domain;

import lombok.Builder;

public class CargoEntity extends Cargo {
    @Builder
    public CargoEntity(int id, double weight, long pieces, WeightUnit weightUnit) {
        super(id, weight, pieces, weightUnit);
    }
}
