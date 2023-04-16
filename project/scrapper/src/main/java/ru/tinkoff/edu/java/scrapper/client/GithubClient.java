package ru.tinkoff.edu.java.scrapper.client;

import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.tinkoff.edu.java.scrapper.client.responses.GithubResponse;

import java.net.URI;

@RequiredArgsConstructor
public class GithubClient {
    private final WebClient githubClient;

    public GithubResponse fetchRepository(String username, String repo) {
        Mono<GithubResponse> githubResponseMono = githubClient
                .get()
                .uri(baseUrl -> URI.create(baseUrl.build() + "repos/" + username + "/" + repo))
                .retrieve()
                .bodyToMono(GithubResponse.class);
        return githubResponseMono
                .block();
    }
}