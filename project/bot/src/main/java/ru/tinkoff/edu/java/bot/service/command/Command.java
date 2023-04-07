package ru.tinkoff.edu.java.bot.service.command;

import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public interface Command {
    String command();
    String arguments();

    String description();

    SendMessage handle(Message message);

    default boolean supports(Update update) {return true;}

    default BotCommand toApiCommand() {
        return new BotCommand(command(), description());
    }
}