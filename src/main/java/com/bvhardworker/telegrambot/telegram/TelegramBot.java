package com.bvhardworker.telegrambot.telegram;

import com.bvhardworker.telegrambot.config.TelegramBotConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramBot extends TelegramLongPollingBot {
    Logger logger = LoggerFactory.getLogger(TelegramBot.class);
    private final TelegramBotConfig telegramBotConfig;

    public TelegramBot(TelegramBotConfig telegramBotConfig) {
        super(telegramBotConfig.getToken());
        this.telegramBotConfig = telegramBotConfig;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

//            switch (messageText) {
//                case "/start":
                sendMessage(chatId, messageText);
//            }
        }
    }

    @Override
    public String getBotUsername() {
        return telegramBotConfig.getName();
    }

    private void sendMessage(Long chatId, String textToSend) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(textToSend + " from chat with id = " + chatId);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            logger.error("unable to send message: {} for chat with chatId = {}", sendMessage.getText(), chatId);
        }
    }

}
