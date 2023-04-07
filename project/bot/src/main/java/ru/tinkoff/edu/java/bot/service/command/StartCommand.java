package ru.tinkoff.edu.java.bot.service.command;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.SendMessage;
import ru.tinkoff.edu.java.bot.service.repo.AbstractTrackedLinksRepo;
import ru.tinkoff.edu.java.bot.service.repo.TrackedLinksRepo;

import java.util.List;

public class StartCommand implements Command {
    private static final String COMMAND = "/start";
    private static final String ARGUMENTS = "";
    private static final String DESCRIPTION = "register user.";
    private static final String DEFAULT_RESPONSE = "Let's start!\nEnter command /help to see all commands.";

    @Override
    public String command() {
        return COMMAND;
    }

    @Override
    public String arguments() {
        return ARGUMENTS;
    }

    @Override
    public String description() {
        return DESCRIPTION;
    }

    @Override
    public SendMessage handle(Message message) {
        return new SendMessage(message.chat().id(), DEFAULT_RESPONSE);
    }
}
