package ru.tinkoff.edu.java.scrapper.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.java.scrapper.client.GithubClient;
import ru.tinkoff.edu.java.scrapper.client.StackOverflowClient;

@Configuration
public class ClientConfiguration {
    @Value("${app.client.baseUrlForGithub:https://api.github.com/}")
    private String baseGithubUrl;
    @Value("${app.client.baseUrlForStackoverflow:https://api.stackexchange.com/2.3/}")
    private String baseStackoverflowUrl;

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