package ru.tinkoff.edu.java.scrapper.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import ru.tinkoff.edu.java.scrapper.dto.ApiErrorResponse;

import java.util.List;

@RestControllerAdvice(annotations = ScrapperHandler.class)
public class ScrapperExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ApiErrorResponse> badRequestHandle(HttpClientErrorException.BadRequest exception) {
        return new ResponseEntity<>(
                new ApiErrorResponse("Bad request", "400", exception.getClass().getName(), exception.getMessage(), List.of()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler
    public ResponseEntity<ApiErrorResponse> badRequestHandle(HttpClientErrorException.NotFound exception) {
        return new ResponseEntity<>(
                new ApiErrorResponse("Not found", "404", exception.getClass().getName(), exception.getMessage(), List.of()),
                HttpStatus.NOT_FOUND
        );
    }
}
