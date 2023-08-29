package com.bvhardworker.telegrambot.commands;

import com.bvhardworker.telegrambot.services.MessageSender;
import lombok.NonNull;
import org.telegram.telegrambots.meta.api.objects.Update;

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
    public abstract void execute(@NonNull Update update);

    public abstract CommandName getCommandName();

    public void setMessageSender(MessageSender messageSender) {
        this.messageSender = messageSender;
    }
}
