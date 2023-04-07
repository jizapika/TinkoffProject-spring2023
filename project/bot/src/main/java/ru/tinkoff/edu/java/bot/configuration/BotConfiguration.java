package ru.tinkoff.edu.java.bot.configuration;

import com.pengrad.telegrambot.TelegramBot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.bot.service.bot.LinkTrackerTelegramBot;

@Configuration
public class BotConfiguration {
    @Bean
    public String token(ApplicationConfig config) {
        return config.token();
    }

    @Bean
    public TelegramBot telegramBot(String token) {
        return new TelegramBot(token);
    }
}
