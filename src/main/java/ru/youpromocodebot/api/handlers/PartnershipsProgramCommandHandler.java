package ru.youpromocodebot.api.handlers;

import com.vdurmont.emoji.EmojiParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import ru.youpromocodebot.api.MessageService;
import ru.youpromocodebot.api.YouPromocodeBot;
import ru.youpromocodebot.api.command.MainCommand;
import ru.youpromocodebot.service.MessagesGenerator;
import ru.youpromocodebot.service.PartnershipsProgramsService;

import java.util.Collections;

@Component
public class PartnershipsProgramCommandHandler implements CommandHandler {

    private final PartnershipsProgramsService partnershipsProgramsService;
    private final MessagesGenerator messagesGenerator;
    private final YouPromocodeBot youPromocodeBot;
    private final MessageService messageService;
    private static final Logger log = LoggerFactory.getLogger(PartnershipsProgramCommandHandler.class);

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
            youPromocodeBot.sendMessage(new SendMessage(chatId, EmojiParser.parseToUnicode(":stopwatch:") + "Секунду, данные загружаются"));
            partnershipsProgramsService.getProgramsFromSites()
                    .stream()
                    .filter(programToUser -> programToUser.getConnectionStatus().equalsIgnoreCase("active"))
                    .forEach(programToUser -> {
                        String button = messageService.getMessage("reply.button.actionList");
                        SendPhoto photoMessageToUser = messagesGenerator.createPhotoMessageToUserWithInlineKeyboard
                                (
                                        chatId, programToUser.getName(),
                                        Collections.singletonMap(button + "|" + programToUser.getId() + "|" + programToUser.getIsDatabaseEntity(), button),
                                        programToUser.getImageUrl(),
                                        programToUser.getIsDatabaseEntity()
                                );
                        youPromocodeBot.sendPhoto(photoMessageToUser);
                    });
            return new SendMessage(chatId, messageService.getMessage("reply.partnerships.loaded"));
        }
        return null;
    }
}
