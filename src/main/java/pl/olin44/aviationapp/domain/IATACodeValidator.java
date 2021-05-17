package pl.olin44.aviationapp.domain;

import pl.olin44.aviationapp.exceptions.InvalidIATACodeException;
import pl.olin44.aviationapp.exceptions.InvalidIATACodeFormatException;
import pl.olin44.aviationapp.exceptions.InvalidIATACodeLengthException;

import java.util.Arrays;
import java.util.List;

public class IATACodeValidator {
    //    TODO: read codes from file?
    private static final List<String> IATA_CODE_LIST = Arrays.asList("SEA", "YYZ", "YYT", "ANC", "LAX", "MIT", "LEW", "GDN", "KRK", "PPX");

    private IATACodeValidator() {
    }

    //    Validation of IATA codes on the basis of https://en.wikipedia.org/wiki/List_of_airports_by_IATA_airport_code:_A
    public static String validate(String iataCode) {
        if (iataCode.length() != 3) {
            throw new InvalidIATACodeLengthException("IATA code must contains 3 letters! Provided: " + iataCode.length());
        }
        if (!iataCode.equals(iataCode.toUpperCase())) {
            throw new InvalidIATACodeFormatException("IATA code must contains only uppercase letters!");
        }
        if (!IATA_CODE_LIST.contains(iataCode)) {
            throw new InvalidIATACodeException("Provided code is not present in available IATA code list");
        }
        return iataCode;
    }


}
