package ru.tinkoff.edu.java.bot.service.command;

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
import ru.tinkoff.edu.java.bot.repo.TrackedLinksRepo;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ListCommandTest {
    private static final Long USER_CHAT_ID = 220283L;
    private static final String DEFAULT_LIST_COMMAND_RESPONSE = "Tracked links:";
    private static final String EMPTY_LIST_RESPONSE = "You don't have tracked links yet.";
    private static final String FIRST_LINK = "https://github.com";
    private static final String SECOND_LINK = "https://stackoverflow.com";
    private static final String MESSAGE_KEY = "text";
    @Mock
    private TrackedLinksRepo repo;
    @InjectMocks
    private final ListCommand command;

    public ListCommandTest() {
        command = new ListCommand(repo);
    }
    @Test
    void handle_findByChatIdReturnsNull_returnEmptyListMessage() {
        // given
        Message message = generateCommandMessage();
        given(repo.findByChatId(USER_CHAT_ID)).willReturn(null);
        // when
        SendMessage sendMessage = command.handle(message);
        // then
        Assertions.assertEquals(
                sendMessage.getParameters().get(MESSAGE_KEY),
                EMPTY_LIST_RESPONSE
        );
    }
    @Test
    void handle_findByChatIdReturnsEmptyList_returnEmptyListMessage() {
        // given
        Message message = generateCommandMessage();
        given(repo.findByChatId(USER_CHAT_ID)).willReturn(new ArrayList<>());
        // when
        SendMessage sendMessage = command.handle(message);
        // then
        Assertions.assertEquals(
                sendMessage.getParameters().get(MESSAGE_KEY),
                EMPTY_LIST_RESPONSE
        );
    }
    @Test
    void handle_findByChatIdReturnList_returnThisListInMessage() {
        // given
        List<String> links = new ArrayList<>();
        links.add(FIRST_LINK);
        links.add(SECOND_LINK);
        Message message = generateCommandMessage();
        given(repo.findByChatId(USER_CHAT_ID)).willReturn(links);
        // when
        SendMessage sendMessage = command.handle(message);
        // then
        Assertions.assertEquals(
                sendMessage.getParameters().get(MESSAGE_KEY),
                DEFAULT_LIST_COMMAND_RESPONSE + "\n" + FIRST_LINK + "\n" + SECOND_LINK
        );
    }
    private static Message generateCommandMessage() {
        String json = """
                {
                    "chat": {
                        "id": 220283
                    },
                    "text": "/list"
                }
                """;
        Gson gson = new Gson();
        return gson.fromJson(json.trim(), new TypeToken<Message>(){}.getType());
    }
}