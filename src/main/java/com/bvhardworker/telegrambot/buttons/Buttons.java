package com.bvhardworker.telegrambot.buttons;

import com.bvhardworker.telegrambot.commands.models.CommandName;
import lombok.Getter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Getter
public enum Buttons {
    START("Start Bot", CommandName.START.getCommandName()),
    HELP("Help", CommandName.HELP.getCommandName()),
    STOP("Stop Bot", CommandName.STOP.getCommandName());

    private final String buttonName;
    private final InlineKeyboardButton inlineKeyboardButton;

    Buttons(String buttonName, String callbackData) {
        this.buttonName = buttonName;
        this.inlineKeyboardButton = new InlineKeyboardButton(buttonName);
        this.inlineKeyboardButton.setCallbackData(callbackData);
    }
}
