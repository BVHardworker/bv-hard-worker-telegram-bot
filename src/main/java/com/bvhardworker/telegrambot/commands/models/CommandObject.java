package com.bvhardworker.telegrambot.commands.models;

import com.bvhardworker.telegrambot.buttons.Buttons;
import lombok.Getter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

@Getter
public enum CommandObject {
    START(CommandName.START, List.of(List.of(Buttons.HELP.getInlineKeyboardButton(),
            Buttons.STOP.getInlineKeyboardButton()))),
    STOP(CommandName.STOP, List.of(List.of(Buttons.START.getInlineKeyboardButton(),
            Buttons.HELP.getInlineKeyboardButton()))),
    HELP(CommandName.HELP);

    private final CommandName commandName;
    private InlineKeyboardMarkup inlineKeyboardMarkup;

    CommandObject(CommandName commandName, List<List<InlineKeyboardButton>> buttons) {
        this.commandName = commandName;
        inlineKeyboardMarkup = new InlineKeyboardMarkup(buttons);
    }

    CommandObject(CommandName commandName) {
        this.commandName = commandName;
    }
}
