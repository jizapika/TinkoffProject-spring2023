package ru.tinkoff.edu.java.scrapper.clients.responses;

import java.time.OffsetDateTime;

public record StackOverflowResponse(StackOverflowResponseItem[] items) {
    public record StackOverflowResponseItem (OffsetDateTime last_activity_date, OffsetDateTime last_edit_date) {
    }
}