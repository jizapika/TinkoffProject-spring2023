package ru.tinkoff.edu.java.bot.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.bot.repo.AbstractTrackedLinksRepo;
import ru.tinkoff.edu.java.bot.service.command.*;

import java.util.Arrays;
import java.util.List;

@Configuration
public class CommandsConfig {
    @Bean
    public List<Command> allCommands(AbstractTrackedLinksRepo repo) {
        return Arrays.asList(
                new HelpCommand(),
                new ListCommand(repo),
                new StartCommand(),
                new TrackCommand(),
                new UntrackCommand()
        );
    }
}
