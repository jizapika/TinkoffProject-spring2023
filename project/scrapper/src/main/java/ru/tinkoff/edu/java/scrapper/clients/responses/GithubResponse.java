package ru.tinkoff.edu.java.scrapper.clients.responses;

import java.time.OffsetDateTime;

public record GithubResponse(OffsetDateTime updated_at) {
}
