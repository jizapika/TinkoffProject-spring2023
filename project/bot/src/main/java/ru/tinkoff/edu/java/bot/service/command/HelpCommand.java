package ru.tinkoff.edu.java.bot.service.command;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.SendMessage;
import ru.tinkoff.edu.java.bot.service.processor.LinkTrackerUserMessageProcessor;
import ru.tinkoff.edu.java.bot.service.processor.UserMessageProcessor;

public class HelpCommand implements Command {
    private static final String COMMAND = "/help";
    private static final String ARGUMENTS = "";
    private static final String DESCRIPTION = "print all commands and their descriptions.";
    private static final String DEFAULT_RESPONSE = "All commands:\n";

    private UserMessageProcessor processor;

    public void setProcessor(UserMessageProcessor processor) {
        this.processor = processor;
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
        StringBuilder textResponse = new StringBuilder(DEFAULT_RESPONSE);
        processor.commands().forEach(command -> textResponse.append(commandInfo(command)));
        return new SendMessage(message.chat().id(), textResponse.toString());
    }

    private static String commandInfo(Command command) {
        return command.command()
                + (command.arguments().trim().isEmpty() ? "" : " ")
                + command.arguments().trim()
                + " - "
                + command.description()
                + "\n";
    }
}
