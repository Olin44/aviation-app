package pl.olin44.aviationapp.exceptions;

public class InvalidIATACodeFormatException extends RuntimeException {
    public InvalidIATACodeFormatException(String message) {
        super(message);
    }
}
