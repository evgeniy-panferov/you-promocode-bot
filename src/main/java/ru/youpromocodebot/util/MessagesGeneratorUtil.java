package ru.youpromocodebot.util;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
public class MessagesGeneratorUtil {

    public static SendMessage createTextMessageToUSer(Update update, String messageToUser){
        log.info("MessagesGeneratorUtil update - {}, messageToUser - {}", update, messageToUser);
        return new SendMessage(update.getMessage().getChatId(),messageToUser);
    }

    public static SendMessage createCallbackQueryToUSer(Update update, String messageToUser){
        log.info("MessagesGeneratorUtil update - {}, messageToUser - {}", update, messageToUser);
        return new SendMessage(update.getCallbackQuery().getId(),messageToUser);
    }
}
