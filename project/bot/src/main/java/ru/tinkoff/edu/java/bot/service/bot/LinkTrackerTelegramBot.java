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
    private final TelegramBot telegramBot;
    private final LinkTrackerUserMessageProcessor processor;

    public LinkTrackerTelegramBot(
            @Autowired String token,
            @Autowired LinkTrackerUserMessageProcessor processor) {
        this.telegramBot = new TelegramBot(token);
        this.processor = processor;
    }

    @Override
    public <T extends BaseRequest<T, R>, R extends BaseResponse> void execute(BaseRequest<T, R> request) {
        telegramBot.execute(request);
    }

    @Override
    public int process(List<Update> updates) {
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
