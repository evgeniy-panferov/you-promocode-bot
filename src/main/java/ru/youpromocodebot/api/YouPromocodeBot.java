package ru.youpromocodebot.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Setter
@Getter
@Component
@RequiredArgsConstructor
public class YouPromocodeBot extends TelegramWebhookBot {

    @Value("${bot.name}")
    private String name;

    @Value("${bot.path}")
    private String path;

    private final TelegramView telegramView;
    private static final Logger log = LoggerFactory.getLogger(YouPromocodeBot.class);

    @Override
    public BotApiMethod onWebhookUpdateReceived(Update update) {
        return telegramView.updateHandler(update);
    }

    public void sendMessage(SendMessage sendMessage) {
        log.info("YouPromocodeBot sendPhoto, sendPhoto-{}", sendMessage);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error("Message can't send - " + e);
        }
    }

    public void sendPhoto(SendPhoto sendPhoto) {
        log.info("YouPromocodeBot sendPhoto, sendPhoto-{}", sendPhoto);
        try {
            execute(sendPhoto);
        } catch (TelegramApiException e) {
            log.error("Photo can't send - " + e);
        }
    }

    @Override
    public String getBotUsername() {
        return name;
    }

    @Override
    public String getBotToken() {
        return System.getenv("TOKEN");
    }

    @Override
    public String getBotPath() {
        return path;
    }

}
