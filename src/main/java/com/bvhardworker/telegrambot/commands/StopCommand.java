package com.bvhardworker.telegrambot.commands;

import com.bvhardworker.telegrambot.commands.models.CommandName;
import com.bvhardworker.telegrambot.commands.models.CommandObject;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.function.Function;

@Service
public class StopCommand extends TelegramBotCommand {
    private static final String MESSAGE = "Bot is stopped";

    @Override
    public CommandObject getCommand() {
        return CommandObject.STOP;
    }

    @Override
    public Function<Message, String> getMessageGetter() {
        return message -> MESSAGE;
    }
}
