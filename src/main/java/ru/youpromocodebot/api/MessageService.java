package ru.youpromocodebot.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class MessageService {

    private final MessageSource messageSource;
    private final Locale locale;

    public MessageService(MessageSource messageSource, @Value("${lang}") String lang) {
        this.messageSource = messageSource;
        this.locale = Locale.forLanguageTag(lang);
    }

    public String getMessage(String message) {
        return messageSource.getMessage(message, null, locale);
    }

    public String getMessage(String message, Object... args) {
        return messageSource.getMessage(message, args, locale);
    }
}

