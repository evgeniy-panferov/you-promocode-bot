package ru.youpromocodebot.api;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.youpromocodebot.api.handlers.CommandHandlerComposite;

@Component
@AllArgsConstructor
public class TelegramView {

    private final CommandHandlerComposite commandHandlerComposite;
    private static final Logger log = LoggerFactory.getLogger(TelegramView.class);

    public BotApiMethod updateHandler(Update update) {
        log.info("TelegramView updateHandler update {}", update);
        return commandHandlerComposite.checkUpdate(update);

    }
}
