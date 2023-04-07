package ru.tinkoff.edu.java.bot.service.command;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.SendMessage;
import ru.tinkoff.edu.java.bot.service.repo.AbstractTrackedLinksRepo;
import ru.tinkoff.edu.java.bot.service.repo.TrackedLinksRepo;

import java.util.List;

public class ListCommand implements Command {
    private static final String COMMAND = "/list";
    private static final String ARGUMENTS = "";
    private static final String DESCRIPTION = "show a list of tracked links.";
    private static final String DEFAULT_RESPONSE = "Tracked links:";

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
        StringBuilder textResponse = new StringBuilder(DEFAULT_RESPONSE);
        TrackedLinksRepo repo = new AbstractTrackedLinksRepo();
        Long chatId = message.chat().id();
        List<String> links = repo.findByChatId(chatId);
        links.forEach(link -> textResponse.append("\n").append(link));
        return new SendMessage(chatId, textResponse.toString());
    }
}
