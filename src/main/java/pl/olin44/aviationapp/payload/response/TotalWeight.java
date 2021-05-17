package pl.olin44.aviationapp.payload.response;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class TotalWeight {
    private final double kg;
    private final double lb;
}
