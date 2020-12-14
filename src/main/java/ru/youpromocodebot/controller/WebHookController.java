package ru.youpromocodebot.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.youpromocodebot.api.YouPromocodeBot;

@RestController
@RequestMapping(value = "/telegram")
@RequiredArgsConstructor
public class WebHookController {

    private static final Logger log = LoggerFactory.getLogger(WebHookController.class);

    private final YouPromocodeBot youPromocodeBot;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public BotApiMethod getUpdate(@RequestBody Update update) {
        log.info("WebHookController getUpdate");
        return youPromocodeBot.onWebhookUpdateReceived(update);
    }
}
