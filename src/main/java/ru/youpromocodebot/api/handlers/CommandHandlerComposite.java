package ru.youpromocodebot.api.handlers;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
import java.util.Objects;

@Component
@AllArgsConstructor
public class CommandHandlerComposite implements CommandHandler {

    private final List<CommandHandler> commandHandlerList;

    private final ErrorHandler errorHandler;

    private static final Logger log = LoggerFactory.getLogger(CommandHandlerComposite.class);

    public SendMessage checkUpdate(Update update) {
        log.info("CommandHandlerComposite resolve update - {}", update);

        if (update.hasMessage() && update.getMessage().hasText()) {
            Long chatId = update.getMessage().getChatId();
            String text = update.getMessage().getText();
            return resolveMatchCommand(chatId, text);
        }

        if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            String[] text = callbackQuery.getData().split("\\|");
            Long chatId = callbackQuery.getMessage().getChatId();
            return resolveMatchCommand(chatId, text);
        }

        return errorHandler.resolve(update.getMessage().getChatId());
    }

    public SendMessage resolveMatchCommand(Long chatId, String... text) {
        return commandHandlerList.stream()
                .map(handler -> handler.resolveMatchCommand(chatId, text))
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(errorHandler.resolve(chatId));
    }
}