package pl.olin44.aviationapp.exceptions;

public class InvalidIATACodeLengthException extends RuntimeException {
    public InvalidIATACodeLengthException(String message) {
        super(message);
    }
}
