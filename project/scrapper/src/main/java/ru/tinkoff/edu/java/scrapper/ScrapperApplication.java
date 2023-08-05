package ru.tinkoff.edu.java.scrapper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.tinkoff.edu.java.scrapper.client.GithubClient;
import ru.tinkoff.edu.java.scrapper.client.StackOverflowClient;
import ru.tinkoff.edu.java.scrapper.configuration.ApplicationConfig;
import ru.tinkoff.edu.java.scrapper.configuration.DataBaseConfig;
import ru.tinkoff.edu.java.scrapper.domain.ChatDao;
import ru.tinkoff.edu.java.scrapper.domain.entity.dto.Chat;

@SpringBootApplication
@EnableConfigurationProperties({ApplicationConfig.class, DataBaseConfig.class})
@EnableScheduling
public class ScrapperApplication {
        public static void main(String[] args) {
                var ctx = SpringApplication.run(ScrapperApplication.class, args);
                ApplicationConfig config = ctx.getBean(ApplicationConfig.class);
             //   System.out.println(String.join("\n", ctx.getBeanNamesForType(long.class)));
                System.out.println(config);
                System.out.println(ctx.getBean(StackOverflowClient.class).fetchQuestion(28275397L).items()[0]);
                System.out.println(ctx.getBean(GithubClient.class).fetchRepository("jizapika", "TinkoffProject-spring2023"));

                ChatDao chatDao = ctx.getBean(ChatDao.class);
                System.out.println(chatDao);
                chatDao.save(new Chat(2390579, "shark.tototodotodo"));
                chatDao.save(new Chat(2390689, "shark.tototodotodo"));

                chatDao.findAll().forEach(System.out::println);
        }
}