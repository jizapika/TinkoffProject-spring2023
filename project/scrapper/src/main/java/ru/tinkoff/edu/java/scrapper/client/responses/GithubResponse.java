package ru.tinkoff.edu.java.scrapper.client.responses;

import java.time.OffsetDateTime;

public record GithubResponse(OffsetDateTime updated_at) {
}
