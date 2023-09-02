package com.bvhardworker.telegrambot.commands;

import com.bvhardworker.telegrambot.commands.models.CommandName;
import com.bvhardworker.telegrambot.commands.models.CommandObject;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.function.Function;

@Service
public class StartCommand extends TelegramBotCommand {
    private static final String MESSAGE = """
            Hello, %s!
            This is telegram bot which will work hard to help you to participate and organise awesome events!
            """;

    @Override
    public CommandObject getCommand() {
        return CommandObject.START;
    }

    @Override
    public Function<Message, String> getMessageGetter() {
        return message -> String.format(MESSAGE, message.getFrom().getFirstName());
    }
}
