package ru.youpromocodebot;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.youpromocodebot.view.TelegramView;


@Setter
@Getter
@Component
@PropertySource("classpath:properties/bot.yml")
@Slf4j
public class YouPromocodeBot extends TelegramWebhookBot {
    @Value("${name}")
    private String name;
    @Value("${token}")
    private String token;
    @Value("${path}")
    private String path;

    private TelegramView telegramView;

    public YouPromocodeBot(TelegramView telegramView) {
        this.telegramView = telegramView;
    }

    @Override
    public BotApiMethod onWebhookUpdateReceived(Update update) {
        return telegramView.updateHandler(update);
    }

    public void sendMessage(long chatId, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error("Message can't send a message" );
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
