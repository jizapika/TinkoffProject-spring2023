package ru.tinkoff.edu.java.bot.service.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.service.processor.LinkTrackerUserMessageProcessor;

import java.util.List;

@Component
public class LinkTrackerTelegramBot implements LinkTrackerBot {
    @Autowired
    private final TelegramBot telegramBot;

    public LinkTrackerTelegramBot(String token) {
        this.telegramBot = new TelegramBot(token);
    }

    @Override
    public <T extends BaseRequest<T, R>, R extends BaseResponse> void execute(BaseRequest<T, R> request) {
        telegramBot.execute(request);
    }

    @Override
    public int process(List<Update> updates) {
        LinkTrackerUserMessageProcessor processor = new LinkTrackerUserMessageProcessor();
        updates.forEach(update -> execute(processor.process(update.message())));
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    @Override
    public void start() {
        telegramBot.setUpdatesListener(this::process);
    }

    @Override
    public void close() {
        telegramBot.shutdown();
    }
}
