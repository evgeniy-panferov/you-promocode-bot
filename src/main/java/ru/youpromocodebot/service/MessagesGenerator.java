package ru.youpromocodebot.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;

import java.util.List;
import java.util.Map;

import static ru.youpromocodebot.util.KeyboardsUtil.getInlineKeyboard;
import static ru.youpromocodebot.util.KeyboardsUtil.getReplyKeyboard;

@Component
@AllArgsConstructor
public class MessagesGenerator {

    private static final Logger log = LoggerFactory.getLogger(MessagesGenerator.class);

    public SendMessage createMessageToUser(Long chatId, String message) {
        log.info("MessagesGeneratorUtil createMessageToUser chatId - {}, messageToUser - {}", chatId, message);
        return new SendMessage(chatId, message);
    }

    public SendMessage createMessageToUserWhitReplyKeyboard(Long chatId, String message, List<String> button) {
        log.info("MessagesGeneratorUtil createMessageToUserWhitReplyKeyboard chatId - {}, messageToUser - {}", chatId, message);
        SendMessage sendMessage = createMessageToUser(chatId, message);
        sendMessage.setReplyMarkup(getReplyKeyboard(button));
        return sendMessage;
    }

    public SendMessage createMessageToUserWithInlineKeyboard(Long chatId, String message, Map<String, String> buttons) {
        log.info("MessagesGeneratorUtil createMessageToUserWithInlineKeyboard chatId - {}, messageToUser - {}", chatId, message);
        SendMessage sendMessage = createMessageToUser(chatId, message);
        sendMessage.setReplyMarkup(getInlineKeyboard(buttons));
        return sendMessage;
    }


    public SendPhoto createPhotoMessageToUserWithInlineKeyboard(Long chatId, String message, Map<String, String> buttons,
                                                                String imageUrl, boolean isDatabaseEntity) {
        log.info("MessagesGeneratorUtil createPhotoMessageToUserWithInlineKeyboard chatId - {}, message - {}, buttons - {}" +
                "imageUrl-{}, isDatabaseEntity-{}", chatId, message, buttons, imageUrl, isDatabaseEntity);
        SendPhoto sendPhoto = createPhotoMessageToUser(chatId, message, imageUrl, isDatabaseEntity);
        sendPhoto.setReplyMarkup(getInlineKeyboard(buttons));
        return sendPhoto;
    }

    public SendPhoto createPhotoMessageToUser(Long chatId, String message, String imageUrl, boolean isDatabaseEntity) {
        log.info("MessagesGeneratorUtil createPhotoMessageToUser chatId - {}, messageToUser - {}, imageUrl-{}", chatId, message, imageUrl);
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setPhoto(imageUrl);
        sendPhoto.setChatId(chatId);
        sendPhoto.setCaption(message);
        return sendPhoto;
    }
}
