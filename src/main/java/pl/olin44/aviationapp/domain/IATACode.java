package pl.olin44.aviationapp.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@Getter
public class IATACode {
    private final String code;

    public IATACode(String code) {
        this.code = IATACodeValidator.validate(code);
    }
}
