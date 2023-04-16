package ru.tinkoff.edu.java.bot.service.command;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.SendMessage;
import ru.tinkoff.edu.java.bot.repo.InMemoryTrackedLinksRepo;
import ru.tinkoff.edu.java.bot.repo.TrackedLinksRepo;

public class TrackCommand implements Command {
    private static final String COMMAND = "/track";
    private static final String ARGUMENTS = "link";
    private static final String DESCRIPTION = "start tracking link.";
    private static final String DEFAULT_RESPONSE = "Link tracked.";

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
        TrackedLinksRepo repo = new InMemoryTrackedLinksRepo();
        Long chatId = message.chat().id();
        String link = message.text().trim().substring(COMMAND.length());
        repo.addByChatIdAndLink(chatId, link);
        return new SendMessage(chatId, DEFAULT_RESPONSE);
    }
}
