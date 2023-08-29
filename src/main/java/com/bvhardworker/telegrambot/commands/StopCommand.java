package com.bvhardworker.telegrambot.commands;

import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class StopCommand extends TelegramBotCommand {
    private static final String MESSAGE = "Bot is stopped";

    @Override
    public void execute(@NonNull Update update) {
        messageSender.sendMessage(update, message -> MESSAGE);
    }

    @Override
    public CommandName getCommandName() {
        return CommandName.STOP;
    }
}
