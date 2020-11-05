package ru.youpromocodebot.api.handlers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.youpromocodebot.api.MessageService;
import ru.youpromocodebot.service.MessagesGenerator;

@Component
@AllArgsConstructor
public class ErrorHandler {

    private MessagesGenerator messagesGenerator;

    private MessageService messageService;

    public SendMessage resolve(Long chatId) {
        String message = messageService.getMessage("reply.notification.error");
        return messagesGenerator.createMessageToUser(chatId, message);
    }
}
