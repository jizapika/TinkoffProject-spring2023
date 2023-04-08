package ru.tinkoff.edu.java.bot.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.java.bot.client.ScrapperClient;

@Configuration
public class ClientConfiguration {
    @Value("${app.client.baseUrlForScrapper:https://localhost:8081/}")
    private String baseScrapperUrl;

    @Bean
    public WebClient scrapperClient() {
        return WebClient.builder()
                .baseUrl(baseScrapperUrl)
                .build();
    }

    @Bean
    public ScrapperClient getScrapperClient() {
        return new ScrapperClient(scrapperClient());
    }
}