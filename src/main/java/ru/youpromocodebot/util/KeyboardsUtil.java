package ru.youpromocodebot.util;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class KeyboardsUtil {

    public static InlineKeyboardMarkup getInlineKeyboard(Map<String, String> buttons) {
        log.info("KeyboardsUtil getInlineKeyboard list - {}", buttons);
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        buttons.keySet().forEach(key -> {
            List<InlineKeyboardButton> list = new ArrayList<>();
            list.add(new InlineKeyboardButton()
                    .setCallbackData(key)
                    .setText(buttons.get(key)));
            keyboard.add(list);
        });

        return new InlineKeyboardMarkup(keyboard);
    }

    public static ReplyKeyboardMarkup getReplyKeyboard(List<String> buttons) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyBoard = new ArrayList<>();

        buttons.forEach(button -> {
            KeyboardRow keyboardRow = new KeyboardRow();
            keyboardRow.add(button);
            keyBoard.add(keyboardRow);
        });
        return replyKeyboardMarkup.setKeyboard(keyBoard);
    }
}
