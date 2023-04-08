package ru.tinkoff.edu.java.bot.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.java.bot.client.responses.LinkResponse;
import ru.tinkoff.edu.java.bot.client.responses.ListLinksResponse;
import ru.tinkoff.edu.java.bot.client.requests.AddLinkRequest;
import ru.tinkoff.edu.java.bot.client.requests.RemoveLinkRequest;

import java.net.URI;

@RequiredArgsConstructor
public class ScrapperClient {
    private final WebClient scrapperClient;

    public boolean registerChat(Long id) {
        return scrapperClient
                .post()
                .uri(baseUrl -> URI.create(baseUrl.build() + "tg-chat/" + id))
                .retrieve()
                .toBodilessEntity()
                .block()
                .getStatusCode()
                .is2xxSuccessful();
    }

    public boolean deleteChat(Long id) {
        return scrapperClient
                .delete()
                .uri(baseUrl -> URI.create(baseUrl.build() + "tg-chat/" + id))
                .retrieve()
                .toBodilessEntity()
                .block()
                .getStatusCode()
                .is2xxSuccessful();
    }

    public ListLinksResponse getAllLinks(Long id) {
        return scrapperClient
                .get()
                .uri(baseUrl -> URI.create(baseUrl.build() + "links?id=" + id))
                .retrieve()
                .bodyToMono(ListLinksResponse.class)
                .block();
    }

    public LinkResponse addLink(AddLinkRequest request) {
        return scrapperClient
                .post()
                .uri(baseUrl -> URI.create(baseUrl.build() + "links"))
                .bodyValue(request)
                .retrieve()
                .bodyToMono(LinkResponse.class)
                .block();
    }

    public LinkResponse removeLink(RemoveLinkRequest request) {
        return scrapperClient
                .post()
                .uri(baseUrl -> URI.create(baseUrl.build() + "links"))
                .bodyValue(request)
                .retrieve()
                .bodyToMono(LinkResponse.class)
                .block();
    }
}