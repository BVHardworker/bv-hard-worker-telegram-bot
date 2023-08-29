package com.bvhardworker.telegrambot.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Service for sending messages via telegram-bot.
 */
public class MessageSender {
    private static final Logger log = LoggerFactory.getLogger(MessageSender.class);
    private final Consumer<BotApiMethod<?>> executor;

    public MessageSender(TelegramLongPollingBot bot) {
        this(method -> {
            try {
                bot.execute(method);
            } catch (TelegramApiException e) {
                log.error("unable to send message for update", e);
            }
        });
    }

    public MessageSender(Consumer<BotApiMethod<?>> executor) {
        this.executor = executor;
    }

    /**
     * Send message via telegram bot.
     *
     * @param chatId provided chatId in which messages would be sent.
     * @param message provided message to be sent.
     */
    public void sendMessage(long chatId, String message) {
        sendMessage(chatId, message, null);
    }

    /**
     * Send message via telegram bot.
     *
     * @param update - object received from telegram UI.
     * @param messageFunction - function to convert message from Update to string message to send back to user
     *
     */
    public void sendMessage(Update update, Function<Message, String> messageFunction) {
        if (update.hasMessage()) {
            Message message = update.getMessage();

            if (message != null) {
                sendMessage(message.getChatId(), messageFunction.apply(message));
            } else {
                log.error("Message is null");
            }
        } else {
            log.error("There are no message. Unable to process");
        }
    }

    /**
     * Send message via telegram bot.
     *
     * @param chatId provided chatId in which messages would be sent.
     * @param message provided message to be sent.
     * @param inlineKeyboardMarkup object represents an inline keyboard that appears right next to the message.
     */
    public void sendMessage(long chatId, String message, InlineKeyboardMarkup inlineKeyboardMarkup) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(message);
        sendMessage.enableHtml(true);

        if (inlineKeyboardMarkup != null) {
            sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        }

        executor.accept(sendMessage);
    }
}
