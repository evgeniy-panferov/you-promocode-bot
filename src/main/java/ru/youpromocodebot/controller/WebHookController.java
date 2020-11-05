package ru.youpromocodebot.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.youpromocodebot.api.YouPromocodeBot;

@RestController
@Slf4j
@RequestMapping(value = "/")
@RequiredArgsConstructor
public class WebHookController {

    private final YouPromocodeBot youPromocodeBot;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public BotApiMethod getUpdate(@RequestBody Update update) {
        return youPromocodeBot.onWebhookUpdateReceived(update);
    }
}
