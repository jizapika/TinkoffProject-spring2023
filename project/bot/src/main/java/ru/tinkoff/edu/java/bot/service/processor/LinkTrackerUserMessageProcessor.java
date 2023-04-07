package ru.tinkoff.edu.java.bot.service.processor;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.SendMessage;
import ru.tinkoff.edu.java.bot.service.command.*;

import java.util.ArrayList;
import java.util.List;

public class LinkTrackerUserMessageProcessor implements UserMessageProcessor {
    private final List<Command> commands = new ArrayList<>();
    public LinkTrackerUserMessageProcessor() {
        commands.add(new HelpCommand());
        commands.add(new ListCommand());
        commands.add(new StartCommand());
        commands.add(new TrackCommand());
        commands.add(new UntrackCommand());
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
