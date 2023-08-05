package ru.tinkoff.edu.java.scrapper.domain.entity.dto;

import java.time.LocalDateTime;

public record Link(Long id,
                   Chat chat,
                   String uri,
                   LocalDateTime lastModified) {
}