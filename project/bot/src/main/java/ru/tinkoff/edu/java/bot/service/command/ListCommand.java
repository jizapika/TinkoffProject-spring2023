package ru.tinkoff.edu.java.bot.service.command;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.repo.TrackedLinksRepo;

import java.util.List;

@Component
public class ListCommand implements Command {
    private static final String COMMAND = "/list";
    private static final String ARGUMENTS = "";
    private static final String DESCRIPTION = "show a list of tracked links.";
    private static final String DEFAULT_RESPONSE = "Tracked links:";
    private static final String EMPTY_LIST_RESPONSE = "You don't have tracked links yet.";
    private TrackedLinksRepo repo;

    public ListCommand(TrackedLinksRepo repo) {
        this.repo = repo;
    }

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
        Long chatId = message.chat().id();
        List<String> links = repo.findByChatId(chatId);
        if (links == null || links.isEmpty()) {
            return new SendMessage(chatId, EMPTY_LIST_RESPONSE);
        }
        StringBuilder textResponse = new StringBuilder(DEFAULT_RESPONSE);
        links.forEach(link -> textResponse.append("\n").append(link));
        return new SendMessage(chatId, textResponse.toString());
    }
}