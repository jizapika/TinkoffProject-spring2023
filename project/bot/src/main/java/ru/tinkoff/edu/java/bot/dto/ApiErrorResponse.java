package ru.tinkoff.edu.java.bot.dto;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public record ApiErrorResponse(String description, String code, String exceptionName, String exceptionMessage, List<String> stacktrace) {
}
