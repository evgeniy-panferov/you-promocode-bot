package ru.youpromocodebot.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Setter
@Getter
@Component
@RequiredArgsConstructor
@PropertySource("classpath:properties/bot.yml")
public class YouPromocodeBot extends TelegramWebhookBot {
    @Value("${name}")
    private String name;
    @Value("${token}")
    private String token;
    @Value("${path}")
    private String path;

    private final TelegramView telegramView;

    @Override
    public BotApiMethod onWebhookUpdateReceived(Update update) {
        return telegramView.updateHandler(update);
    }

    public void sendPhoto(SendPhoto sendPhoto) {
        log.info("YouPromocodeBot sendPhoto, sendPhoto-{}", sendPhoto);
        try {
            execute(sendPhoto);
        } catch (TelegramApiException e) {
            log.error("Photo can't send");
        }
    }

    @Override
    public String getBotUsername() {
        return name;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public String getBotPath() {
        return path;
    }

}
