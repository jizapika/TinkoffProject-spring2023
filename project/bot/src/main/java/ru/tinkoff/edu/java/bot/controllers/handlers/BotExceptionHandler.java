package ru.tinkoff.edu.java.bot.controllers.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import ru.tinkoff.edu.java.bot.dto.ApiErrorResponse;

import java.util.List;

@RestControllerAdvice(annotations = BotHandler.class)
public class BotExceptionHandler {

    private static final String BAD_REQUEST_DESCRIPTION = "Bad request";
    private static final String BAD_REQUEST_CODE = "400";

    @ExceptionHandler
    public ResponseEntity<ApiErrorResponse> badRequestHandle(HttpClientErrorException.BadRequest exception) {
        return new ResponseEntity<>(
                new ApiErrorResponse(BAD_REQUEST_DESCRIPTION, BAD_REQUEST_CODE, exception.getClass().getName(), exception.getMessage(), List.of()),
                HttpStatus.BAD_REQUEST
        );
    }
}
