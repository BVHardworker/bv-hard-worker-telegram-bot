package com.bvhardworker.telegrambot.commands.models;

import lombok.Getter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

@Getter
public enum CommandName {
    START("/start", "Command to start bot"),
    STOP("/stop", "Command to stop bot"),
    HELP("/help", "Command to help user");

    private final String commandName;
    private final String description;

    CommandName(String commandName, String description) {
        this.commandName = commandName;
        this.description = description;
    }
}
