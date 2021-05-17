package pl.olin44.aviationapp.domain;

import lombok.Builder;

public class BaggageEntity extends Cargo {
    @Builder
    public BaggageEntity(int id, double weight, int pieces, WeightUnit weightUnit) {
        super(id, weight, pieces, weightUnit);
    }
}
