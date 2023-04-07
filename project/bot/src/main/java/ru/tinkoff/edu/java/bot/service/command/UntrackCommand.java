package ru.tinkoff.edu.java.bot.service.command;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.SendMessage;
import ru.tinkoff.edu.java.bot.service.repo.AbstractTrackedLinksRepo;
import ru.tinkoff.edu.java.bot.service.repo.TrackedLinksRepo;

import java.util.List;
import java.util.Optional;

public class UntrackCommand implements Command {
    private static final String COMMAND = "/untrack";
    private static final String ARGUMENTS = "link";
    private static final String DESCRIPTION = "stop tracking link.";
    private static final String DELETED_RESPONSE = "Link untracked.";
    private static final String NOT_FOUND_RESPONSE = "Link wasn't found.";

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
        TrackedLinksRepo repo = new AbstractTrackedLinksRepo();
        Long chatId = message.chat().id();
        String link = message.text().trim().substring(COMMAND.length());
        Optional<String> deletedLink = repo.deleteByChatIdAndLink(chatId, link);
        return new SendMessage(chatId, deletedLink.isEmpty() ? NOT_FOUND_RESPONSE : DELETED_RESPONSE);
    }
}
