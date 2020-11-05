package ru.youpromocodebot.api.handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;


/**
 * Not use
 */
@Slf4j
@Component
public class WebmasterSitesCommandHandler implements CommandHandler {

    @Override
    public SendMessage resolveMatchCommand(Long chatId, String... text) {
        log.info("WebmasterSitesCommandHandler findMatchCommand - text {} , chatId {}", text, chatId);
        return null;
    }
}
