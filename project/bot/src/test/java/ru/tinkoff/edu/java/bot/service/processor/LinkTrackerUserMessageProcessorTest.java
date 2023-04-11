package ru.tinkoff.edu.java.bot.service.processor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.SendMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.tinkoff.edu.java.bot.repo.AbstractTrackedLinksRepo;
import ru.tinkoff.edu.java.bot.service.command.*;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class LinkTrackerUserMessageProcessorTest {
    private static final String UNKNOWN_COMMAND_RESPONSE = "The command is unknown :(";
    private static final String MESSAGE_KEY = "text";
    private UserMessageProcessor processor;

    @Test
    void process_unknownCommandReceived_returnUnknownCommandMessage() {
        // given
        Message message = generateCommandMessage();
        processor = new LinkTrackerUserMessageProcessor(defaultCommandList());
        // when
        SendMessage sendMessage = processor.process(message);
        // then
        Assertions.assertEquals(
                sendMessage.getParameters().get(MESSAGE_KEY),
                UNKNOWN_COMMAND_RESPONSE
        );
    }
    private static Message generateCommandMessage() {
        String json = """
                {
                    "chat": {
                        "id": 220283
                    },
                    "text": "/sponsor"
                }
                """;
        Gson gson = new Gson();
        return gson.fromJson(json.trim(), new TypeToken<Message>(){}.getType());
    }

    private List<Command> defaultCommandList() {
        return Arrays.asList(
                new HelpCommand(),
                new StartCommand(),
                new TrackCommand(),
                new UntrackCommand()
        );
    }
}