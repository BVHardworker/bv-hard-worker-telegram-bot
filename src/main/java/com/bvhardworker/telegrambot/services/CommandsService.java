package com.bvhardworker.telegrambot.services;

import com.bvhardworker.telegrambot.commands.TelegramBotCommand;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CommandsService {
    private static final Logger log = LoggerFactory.getLogger(CommandsService.class);
    private static final String COMMAND_PREFIX = "/";
    private final Map<String, TelegramBotCommand> commandsMap;

    public CommandsService(List<TelegramBotCommand> commands) {
        commandsMap = commands.stream()
                .collect(Collectors.toMap(command -> command.getCommandName().getCommandName(), command -> command));
    }

    private static boolean pressedButton(@NonNull Update update) {
        return update.hasCallbackQuery();
    }

    private static boolean receivedTextCommand(@NonNull Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();

            if (message != null && message.hasText()) {
                String text = message.getText();

                return text != null && text.startsWith(COMMAND_PREFIX);
            }
        }

        return false;
    }

    public void executeCommand(@NonNull Update update) {
        if (receivedTextCommand(update)) {
            executeCommand(update.getMessage().getText(), update);
        } else if (pressedButton(update)) {
            executeCommand(update.getCallbackQuery().getData(), update);
        }
    }

    public void executeCommand(@NonNull String command, @NonNull Update update) {
        if (commandsMap.containsKey(command)) {
            TelegramBotCommand telegramBotCommand = commandsMap.get(command);

            if (telegramBotCommand != null) {
                telegramBotCommand.execute(update);
            } else {
                log.error("Command is null for name = {}", command);
            }
        } else {
            log.error("Unknown command: {}", command);
        }
    }

    public boolean receivedCommand(@NonNull Update update) {
        return pressedButton(update) || receivedTextCommand(update);
    }

    public List<BotCommand> getCommands() {
        return commandsMap.values()
                .stream()
                .map(TelegramBotCommand::getCommandName)
                .map(command -> new BotCommand(command.getCommandName(), command.getDescription()))
                .collect(Collectors.toList());
    }

    public void setMessageSender(MessageSender messageSender) {
        commandsMap.values()
                .forEach(telegramBotCommand -> telegramBotCommand.setMessageSender(messageSender));
    }
}
