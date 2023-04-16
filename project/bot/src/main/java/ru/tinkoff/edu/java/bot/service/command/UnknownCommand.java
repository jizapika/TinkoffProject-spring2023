package ru.tinkoff.edu.java.bot.service.command;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.SendMessage;

public class UnknownCommand implements Command {
    private static final String COMMAND = "unknown command";
    private static final String ARGUMENTS = "";
    private static final String DESCRIPTION = "tell the user that the command is unknown.";
    private static final String DEFAULT_RESPONSE = "The command is unknown :(";
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
