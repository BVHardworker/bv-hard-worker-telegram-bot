package com.bvhardworker.telegrambot.commands;

import com.bvhardworker.telegrambot.commands.models.CommandObject;
import com.bvhardworker.telegrambot.services.MessageSender;
import lombok.NonNull;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.function.Function;

/**
 * Command abstract class for handling telegram-bot commands.
 */
public abstract class TelegramBotCommand {
    protected MessageSender messageSender;

    /**
     * Main method, which is executing command logic.
     *
     * @param update provided {@link Update} object with all the needed data for command.
     */
    public void execute(@NonNull Update update) {
        messageSender.sendMessage(update, getMessageGetter(), getCommand());
    }

    public abstract CommandObject getCommand();

    public abstract Function<Message, String> getMessageGetter();

    public void setMessageSender(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public String getCommandName() {
        return getCommand().getCommandName().getCommandName();
    }
}
