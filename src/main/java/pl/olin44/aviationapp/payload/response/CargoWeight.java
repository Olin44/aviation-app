package pl.olin44.aviationapp.payload.response;

import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = false)
@Value
@SuperBuilder
public class CargoWeight extends TotalWeight {

}
