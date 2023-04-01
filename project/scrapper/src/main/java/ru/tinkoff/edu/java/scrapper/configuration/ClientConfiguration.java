package ru.tinkoff.edu.java.scrapper.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.java.scrapper.clients.GithubClient;
import ru.tinkoff.edu.java.scrapper.clients.StackOverflowClient;

@Configuration
public class ClientConfiguration {
    private static String baseGithubUrl = "https://api.github.com/";
    private static String baseStackoverflowUrl = "https://api.stackexchange.com/2.3/";

    @Bean
    public WebClient githubClient() {
        return WebClient.builder()
                .baseUrl(baseGithubUrl)
                .build();
    }

    @Bean
    public WebClient stackOverflowClient() {
        return WebClient.builder()
                .baseUrl(baseStackoverflowUrl)
                .build();
    }

    @Bean
    public StackOverflowClient getStackOverflowClient() {
        return new StackOverflowClient(stackOverflowClient());
    }

    @Bean
    public GithubClient getGithubClient() {
        return new GithubClient(githubClient());
    }
}