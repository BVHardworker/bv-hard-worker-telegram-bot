package com.bvhardworker.telegrambot.commands;

import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Arrays;

@Service
public class HelpCommand extends TelegramBotCommand {
    private static final String MESSAGE = "<b>Available commands:</b>\n\n";

    @Override
    public void execute(@NonNull Update update) {
        StringBuilder stringBuilder = new StringBuilder(MESSAGE);
        Arrays.stream(CommandName.values())
                .forEach(command -> stringBuilder.append(command.getCommandName())
                .append(" - ")
                .append(command.getDescription())
                .append("\n"));
        messageSender.sendMessage(update, message -> stringBuilder.toString());
    }

    @Override
    public CommandName getCommandName() {
        return CommandName.HELP;
    }
}
