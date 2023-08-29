package com.bvhardworker.telegrambot.commands;

import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class StartCommand extends TelegramBotCommand {
    private static final String MESSAGE = """
            Hello, %s!
            This is telegram bot which will work hard to help you to participate and organise awesome events!
            """;

    @Override
    public void execute(@NonNull Update update) {
        messageSender.sendMessage(update, message -> String.format(MESSAGE, message.getFrom().getFirstName()));
    }

    @Override
    public CommandName getCommandName() {
        return CommandName.START;
    }
}
