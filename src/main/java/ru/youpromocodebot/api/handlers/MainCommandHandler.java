package ru.youpromocodebot.api.handlers;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.youpromocodebot.api.MessageService;
import ru.youpromocodebot.api.command.MainCommand;
import ru.youpromocodebot.service.MessagesGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class MainCommandHandler implements CommandHandler {

    private final MessagesGenerator messagesGenerator;
    private final MessageService messageService;

    private static final Logger log = LoggerFactory.getLogger(MainCommandHandler.class);

    public SendMessage resolveMatchCommand(Long chatId, String... text) {
        log.info("MainCommandHandler findMatchCommand - text {} , chatId {}", text, chatId);
        String command = text[0];
        if (command.equalsIgnoreCase(MainCommand.HELP.getCommand()) || command.equalsIgnoreCase("/start")) {

            List<String> button = Arrays.stream(MainCommand.values())
                    .map(MainCommand::getButton)
                    .collect(Collectors.toList());

            return messagesGenerator.createMessageToUserWhitReplyKeyboard(chatId, messageService.getMessage("reply.main.start"), button);
        }

        if (command.equalsIgnoreCase(MainCommand.SEARCH_PARTNERSHIP.getCommand())) {
            return new SendMessage(chatId, "пока не работает");
        }

        return null;
    }

}
