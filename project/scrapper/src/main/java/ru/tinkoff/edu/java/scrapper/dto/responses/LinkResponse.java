package ru.tinkoff.edu.java.scrapper.dto.responses;

import java.net.URI;

public record LinkResponse(Long id, URI url) {
}