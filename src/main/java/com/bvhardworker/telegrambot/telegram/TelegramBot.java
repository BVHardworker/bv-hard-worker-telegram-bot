package com.bvhardworker.telegrambot.telegram;

import com.bvhardworker.telegrambot.services.CommandsService;
import com.bvhardworker.telegrambot.config.TelegramBotConfig;
import com.bvhardworker.telegrambot.services.MessageSender;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramBot extends TelegramLongPollingBot {
    private final TelegramBotConfig telegramBotConfig;
    private final CommandsService commandsService;

    public TelegramBot(TelegramBotConfig telegramBotConfig, CommandsService commandsService) throws TelegramApiException {
        super(telegramBotConfig.getToken());
        this.telegramBotConfig = telegramBotConfig;
        this.commandsService = commandsService;
        commandsService.setMessageSender(new MessageSender(this));
        execute(new SetMyCommands(commandsService.getCommands(), new BotCommandScopeDefault(), null));
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (commandsService.receivedCommand(update)) {
            commandsService.executeCommand(update);
        }
    }

    @Override
    public String getBotUsername() {
        return telegramBotConfig.getName();
    }
}
