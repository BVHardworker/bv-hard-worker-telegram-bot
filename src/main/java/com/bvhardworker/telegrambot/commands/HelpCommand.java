package com.bvhardworker.telegrambot.commands;

import com.bvhardworker.messages.drawgenerator.DrawType;
import com.bvhardworker.telegrambot.commands.models.CommandName;
import com.bvhardworker.telegrambot.commands.models.CommandObject;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Arrays;
import java.util.function.Function;

@Service
public class HelpCommand extends TelegramBotCommand {
    private static final String MESSAGE = "<b>Available commands:</b>\n\n";

    @Override
    public CommandObject getCommand() {
        return CommandObject.HELP;
    }

    @Override
    public Function<Message, String> getMessageGetter() {
        StringBuilder stringBuilder = new StringBuilder(MESSAGE);
        Arrays.stream(CommandName.values())
                .forEach(command -> stringBuilder.append(command.getCommandName())
                        .append(" - ")
                        .append(command.getDescription())
                        .append("\n"));
        return message -> stringBuilder.toString();
    }
}
