package pl.olin44.aviationapp.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class Cargo {
    private final int id;
    private final double weight;
    private final long pieces;
    private final WeightUnit weightUnit;

}
