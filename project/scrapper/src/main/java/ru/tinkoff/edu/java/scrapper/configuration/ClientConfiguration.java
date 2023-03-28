package ru.tinkoff.edu.java.scrapper.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ClientConfiguration {
    private String baseUrl = "https://jsonplaceholder.typicode.com";
    public static final int TIMEOUT = 1000;

    public ClientConfiguration(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Bean
    public WebClient githubClient() {
        return WebClient.builder()
                .baseUrl("/questions/{id}")
                .build();
    }
}