package ru.tinkoff.edu.java.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SetChatPhoto;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.SendResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.tinkoff.edu.java.bot.configuration.ApplicationConfig;
import ru.tinkoff.edu.java.bot.service.bot.LinkTrackerTelegramBot;

import java.io.File;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationConfig.class)
public class BotApplication {
        public static void main(String[] args) {
                var ctx = SpringApplication.run(BotApplication.class, args);
                ApplicationConfig config = ctx.getBean(ApplicationConfig.class);
                System.out.println(config);
                serve(ctx.getBean(ApplicationConfig.class).token());
        }

        public static void serve(String token) {
                LinkTrackerTelegramBot bot = new LinkTrackerTelegramBot(token);
                bot.start();
        }
}