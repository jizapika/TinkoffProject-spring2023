package ru.tinkoff.edu.java.scrapper.clients;

import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.java.scrapper.clients.responses.StackOverflowResponse;

public class StackOverflowClient {
    WebClient webClient;

    public StackOverflowClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public StackOverflowResponse getQuestions() {
        Long id = 28275397L;
        webClient.get().uri(baseUrl + "/questions/{id}");
    }
}
