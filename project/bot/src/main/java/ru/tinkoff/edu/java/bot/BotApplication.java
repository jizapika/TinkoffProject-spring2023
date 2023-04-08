package ru.tinkoff.edu.java.bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.tinkoff.edu.java.bot.client.ScrapperClient;
import ru.tinkoff.edu.java.bot.client.requests.AddLinkRequest;
import ru.tinkoff.edu.java.bot.client.requests.RemoveLinkRequest;
import ru.tinkoff.edu.java.bot.configuration.ApplicationConfig;
import ru.tinkoff.edu.java.bot.service.bot.LinkTrackerTelegramBot;

import java.net.URI;
import java.net.URISyntaxException;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationConfig.class)
public class BotApplication {
        public static void main(String[] args) throws URISyntaxException {
                var ctx = SpringApplication.run(BotApplication.class, args);
                ApplicationConfig config = ctx.getBean(ApplicationConfig.class);
                System.out.println(config);
                System.out.println(ctx.getBean(ScrapperClient.class).registerChat(28275397L));
                System.out.println(ctx.getBean(ScrapperClient.class).deleteChat(28275397L));
                System.out.println(ctx.getBean(ScrapperClient.class).getAllLinks(28275397L));
                System.out.println(ctx.getBean(ScrapperClient.class).addLink(new AddLinkRequest(new URI("https://github.com"))));
                System.out.println(ctx.getBean(ScrapperClient.class).removeLink(new RemoveLinkRequest(new URI("https://stackoverflow.com"))));
                serve(ctx.getBean(ApplicationConfig.class).token());
        }

        public static void serve(String token) {
                LinkTrackerTelegramBot bot = new LinkTrackerTelegramBot(token);
                bot.start();
        }
}