package ru.tinkoff.edu.java.bot.service.processor;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.service.command.*;

import java.util.ArrayList;
import java.util.List;

@Component
public class LinkTrackerUserMessageProcessor implements UserMessageProcessor {
    private final List<Command> commands;
    public LinkTrackerUserMessageProcessor(
            @Autowired @Qualifier("allCommands") List<Command> commands) {
        this.commands = commands;
        for (Command command : commands) {
            if (command instanceof HelpCommand helpCommand) {
                helpCommand.setProcessor(this);
            }
        }
    }

    @Override
    public List<? extends Command> commands() {
        return commands;
    }

    @Override
    public SendMessage process(Message message) {
        for (Command command : commands) {
            if (message.text().trim().startsWith(command.command())) {
                return command.handle(message);
            }
        }
        return new UnknownCommand().handle(message);
    }
}
