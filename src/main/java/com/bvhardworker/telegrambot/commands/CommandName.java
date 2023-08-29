package com.bvhardworker.telegrambot.commands;

import lombok.Getter;

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
