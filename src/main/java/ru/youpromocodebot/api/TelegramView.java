package ru.youpromocodebot.api;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.youpromocodebot.api.handlers.CommandHandlerComposite;


@Slf4j
@Component
@AllArgsConstructor
public class TelegramView {

    private final CommandHandlerComposite commandHandlerComposite;

    public BotApiMethod updateHandler(Update update) {
        log.info("TelegramView updateHandler update {}", update);
        return commandHandlerComposite.checkUpdate(update);

    }
}
