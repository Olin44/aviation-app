package pl.olin44.aviationapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.olin44.aviationapp.exceptions.EntityNotFoundException;
import pl.olin44.aviationapp.exceptions.InvalidIATACodeException;
import pl.olin44.aviationapp.exceptions.InvalidIATACodeFormatException;
import pl.olin44.aviationapp.exceptions.InvalidIATACodeLengthException;
import pl.olin44.aviationapp.payload.response.ErrorResponse;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({EntityNotFoundException.class, InvalidIATACodeException.class, InvalidIATACodeLengthException.class, InvalidIATACodeFormatException.class})
    public ResponseEntity<ErrorResponse> entityNotFoundExceptionHandler(Exception ex, WebRequest request) {
        ErrorResponse errors = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .error(ex.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .build();
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }
}
