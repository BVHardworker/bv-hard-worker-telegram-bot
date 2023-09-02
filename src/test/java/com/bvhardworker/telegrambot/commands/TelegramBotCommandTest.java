package com.bvhardworker.telegrambot.commands;

import com.bvhardworker.telegrambot.services.MessageSender;
import com.bvhardworker.telegrambot.telegram.TelegramBot;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Abstract class for testing {@link TelegramBotCommand}s.
 */
public abstract class TelegramBotCommandTest {
    private static final long CHAT_ID = 1234567824356L;
    protected TelegramBot telegramBot = Mockito.mock(TelegramBot.class);
    protected MessageSender sendBotMessageService = new MessageSender(telegramBot);

    protected abstract String getCommandMessage();

    protected abstract TelegramBotCommand getCommand();

    @Test
    public void shouldProperlyExecuteCommand() throws TelegramApiException {
        Update update = new Update();
        Message message = Mockito.mock(Message.class);
        Mockito.when(message.getChatId())
                .thenReturn(CHAT_ID);
        TelegramBotCommand command = getCommand();
        Mockito.when(message.getText())
                .thenReturn(command.getCommandName());
        update.setMessage(message);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(CHAT_ID);
        sendMessage.setText(getCommandMessage());
        sendMessage.enableHtml(true);

        //when
        getCommand().execute(update);

        //then
        Mockito.verify(telegramBot)
                .execute(sendMessage);
    }
}
