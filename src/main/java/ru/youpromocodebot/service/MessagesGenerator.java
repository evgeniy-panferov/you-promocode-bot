package ru.youpromocodebot.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import ru.youpromocodebot.util.KeyboardsUtil;

import java.util.List;
import java.util.Map;

@Slf4j
@Component
@AllArgsConstructor
public class MessagesGenerator {

    public SendMessage createMessageToUser(Long chatId, String message) {
        log.info("MessagesGeneratorUtil createMessageToUser chatId - {}, messageToUser - {}", chatId, message);
        return new SendMessage(chatId, message);
    }

    public SendPhoto createPhotoMessageToUser(Long chatId, String message, String imageUrl) {
        log.info("MessagesGeneratorUtil createPhotoMessageToUser chatId - {}, messageToUser - {}", chatId, message);
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chatId);
        sendPhoto.setPhoto(imageUrl);
        sendPhoto.setCaption(message);
        return sendPhoto;
    }

    public SendMessage createMessageToUserWhitReplyKeyboard(Long chatId, String message, List<String> button) {
        log.info("MessagesGeneratorUtil createMessageToUserWhitReplyKeyboard chatId - {}, messageToUser - {}", chatId, message);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
        sendMessage.setReplyMarkup(KeyboardsUtil.getReplyKeyboard(button));
        return sendMessage;
    }

    public SendMessage createMessageToUserWithInlineKeyboard(Long chatId, String message, Map<String, String> buttons) {
        log.info("MessagesGeneratorUtil createMessageToUserWithInlineKeyboard chatId - {}, messageToUser - {}", chatId, message);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
        sendMessage.setReplyMarkup(KeyboardsUtil.getInlineKeyboard(buttons));
        return sendMessage;
    }
}
