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

    private static final String BAD_REQUEST_DESCRIPTION = "Bad request";
    private static final String BAD_REQUEST_CODE = "400";
    public static final String NOT_FOUND_DESCRIPTION = "Not found";
    public static final String NOT_FOUND_CODE = "404";

    @ExceptionHandler
    public ResponseEntity<ApiErrorResponse> badRequestHandle(HttpClientErrorException.BadRequest exception) {
        return new ResponseEntity<>(
                new ApiErrorResponse(BAD_REQUEST_DESCRIPTION, BAD_REQUEST_CODE, exception.getClass().getName(), exception.getMessage(), List.of()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler
    public ResponseEntity<ApiErrorResponse> badRequestHandle(HttpClientErrorException.NotFound exception) {
        return new ResponseEntity<>(
                new ApiErrorResponse(NOT_FOUND_DESCRIPTION, NOT_FOUND_CODE, exception.getClass().getName(), exception.getMessage(), List.of()),
                HttpStatus.NOT_FOUND
        );
    }
}
