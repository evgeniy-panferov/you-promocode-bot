package ru.youpromocodebot.api.handlers;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface CommandHandler {
    SendMessage resolveMatchCommand(Long chatId, String... text);
}