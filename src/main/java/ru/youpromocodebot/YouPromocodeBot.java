package ru.youpromocodebot;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.youpromocodebot.service.TelegramView;

@Component
@Setter
@Getter
@PropertySource("classpath:properties/bot.yml")
public class YouPromocodeBot extends TelegramWebhookBot {
    @Value("${name}")
    private String name;
    @Value("${token}")
    private String token;
    @Value("${path}")
    private String path;

    TelegramView telegramView;

    public YouPromocodeBot(TelegramView telegramView) {
        this.telegramView = telegramView;
    }

    @Override
    public BotApiMethod onWebhookUpdateReceived(Update update) {
        return telegramView.updateHandler(update);
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
