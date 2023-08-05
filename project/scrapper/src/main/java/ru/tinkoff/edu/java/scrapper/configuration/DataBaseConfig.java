package ru.tinkoff.edu.java.scrapper.configuration;

import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.time.Duration;

@Validated
@ConfigurationProperties(prefix = "db", ignoreUnknownFields = false)
public record DataBaseConfig(String driver,
                             String url,
                             String username,
                             String password) {
}