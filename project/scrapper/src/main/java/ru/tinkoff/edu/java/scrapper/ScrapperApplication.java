package ru.tinkoff.edu.java.scrapper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.tinkoff.edu.java.scrapper.clients.GithubClient;
import ru.tinkoff.edu.java.scrapper.clients.StackOverflowClient;
import ru.tinkoff.edu.java.scrapper.configuration.ApplicationConfig;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationConfig.class)
public class ScrapperApplication {
        public static void main(String[] args) {
                var ctx = SpringApplication.run(ScrapperApplication.class, args);
                ApplicationConfig config = ctx.getBean(ApplicationConfig.class);
                System.out.println(config);
                System.out.println(ctx.getBean(StackOverflowClient.class).fetchQuestion(28275397L).items()[0]);
                System.out.println(ctx.getBean(GithubClient.class).fetchRepository("jizapika", "TinkoffProject-spring2023"));
        }
}