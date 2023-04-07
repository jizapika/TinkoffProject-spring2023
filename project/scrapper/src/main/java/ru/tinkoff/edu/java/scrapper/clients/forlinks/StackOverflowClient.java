package ru.tinkoff.edu.java.scrapper.clients.forlinks;

import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.tinkoff.edu.java.scrapper.clients.forlinks.responses.StackOverflowResponse;

import java.net.URI;

@RequiredArgsConstructor
public class StackOverflowClient {
    private final WebClient stackoverflowClient;

    public StackOverflowResponse fetchQuestion(Long id) {
        Mono<StackOverflowResponse> stackOverflowResponseMono = stackoverflowClient
                .get()
                .uri(baseUrl -> URI.create(baseUrl.build() + "questions/" + id + "?site=stackoverflow"))
                .retrieve()
                .bodyToMono(StackOverflowResponse.class);
        return stackOverflowResponseMono
                .block();
    }
}