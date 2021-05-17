package pl.olin44.aviationapp.exceptions;

public class InvalidIATACodeException extends RuntimeException {
    public InvalidIATACodeException(String message) {
        super(message);
    }
}
