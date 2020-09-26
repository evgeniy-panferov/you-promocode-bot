package ru.youpromocodebot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.youpromocodebot.State;
import ru.youpromocodebot.util.FieldClassUtil;

import java.lang.reflect.Field;

@Component
@Slf4j
public class TelegramView {

    public BotApiMethod updateHandler(Update update) {
        log.info("updateHandler chatId {}, update message{}", update.getMessage().getChatId(), update.getMessage());
        SendMessage sendMessage;
        if (update.hasMessage() && update.getMessage().hasText()) {
            sendMessage = inputTextHandler(update);
            return sendMessage;
        }
        return null;
    }

    public SendMessage inputTextHandler(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId().toString());
        String messageToUser = switch (update.getMessage().getText().toLowerCase().trim()) {
            case State.GET_LIST_COMMAND -> {
                log.info("switch(Получить список команд)");
                yield FieldClassUtil.getCommands(State.class)
                        .toString();
            }
            case State.GET_PROMOCODE -> {
                log.info("switch(Получить промокод)");
                yield "asgfds123sfd";
            }
            default -> {
                log.info("switch(default)");
                yield FieldClassUtil.getCommands(State.class)
                        .insert(0, "Команда не найдена.\nСписок доступных:\n")
                        .toString();
            }
        };
        sendMessage.setText(messageToUser);
        return sendMessage;
    }


}
