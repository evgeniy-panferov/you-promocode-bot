package ru.youpromocodebot.api.handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.youpromocodebot.api.MessageService;
import ru.youpromocodebot.api.YouPromocodeBot;
import ru.youpromocodebot.api.command.MainCommand;
import ru.youpromocodebot.service.MessagesGenerator;
import ru.youpromocodebot.service.PartnershipsProgramsService;

import java.util.Collections;

@Slf4j
@Component
public class PartnershipsProgramCommandHandler implements CommandHandler {

    private final PartnershipsProgramsService partnershipsProgramsService;
    private final MessagesGenerator messagesGenerator;
    private final YouPromocodeBot youPromocodeBot;
    private final MessageService messageService;

    public PartnershipsProgramCommandHandler(PartnershipsProgramsService partnershipsProgramsService,
                                             MessagesGenerator messagesGenerator, @Lazy YouPromocodeBot youPromocodeBot,
                                             MessageService messageService) {
        this.partnershipsProgramsService = partnershipsProgramsService;
        this.messagesGenerator = messagesGenerator;
        this.youPromocodeBot = youPromocodeBot;
        this.messageService = messageService;
    }

    public SendMessage resolveMatchCommand(Long chatId, String... text) {
        log.info("PartnershipsProgramCommandHandler findMatchCommand - text {}, chatId {}", text, chatId);
        String command = text[0];

        if (command.equalsIgnoreCase(MainCommand.PARTNERSHIPS_LIST.getCommand())) {
            partnershipsProgramsService.getProgramsFromSites()
                    .forEach(programToUser -> {
                        String button = messageService.getMessage("reply.button.actionList");
                        SendMessage messageToUser = messagesGenerator.createMessageToUserWithInlineKeyboard(
                                chatId, programToUser.getName(),
                                Collections.singletonMap(button + "|" + programToUser.getId(), button));
                        youPromocodeBot.sendMessage(messageToUser);
                    });
            return new SendMessage(chatId, messageService.getMessage("reply.main.loaded"));
        }
        return null;
    }
}
