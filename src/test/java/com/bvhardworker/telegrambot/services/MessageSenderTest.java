package com.bvhardworker.telegrambot.services;

import com.bvhardworker.telegrambot.telegram.TelegramBot;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@DisplayName("Unit-level testing for MessageSender")
public class MessageSenderTest {
    private static final long CHAT_ID = 123;
    private static final String TEST_MESSAGE = "TEST";

    private static final TelegramBot TELEGRAM_BOT = Mockito.mock(TelegramBot.class);

    private static final MessageSender MESSAGE_SENDER = new MessageSender(TELEGRAM_BOT);

    @Test
    public void sendTextMessageTest() throws TelegramApiException {
        MESSAGE_SENDER.sendMessage(CHAT_ID, TEST_MESSAGE);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(CHAT_ID));
        sendMessage.setText(TEST_MESSAGE);
        sendMessage.enableHtml(true);

        Mockito.verify(TELEGRAM_BOT)
                .execute(sendMessage);
    }
}
