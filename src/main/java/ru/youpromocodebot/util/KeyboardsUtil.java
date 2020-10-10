package ru.youpromocodebot.util;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class KeyboardsUtil {

    public static InlineKeyboardMarkup getInlineKeyboard(List<String> list, int countButton) {
        log.info("KeyboardsUtil getInlineKeyboard list - {}, countButton - {}", list, countButton);
        List<List<InlineKeyboardButton>> doubleButtonList = new ArrayList<>();
        List<InlineKeyboardButton> buttonList = new ArrayList<>();

        int j = countButton;
        for (int i = list.size() - 1; i >= 0; i--) {
            if (j == 0) {
                doubleButtonList.add(buttonList);
                buttonList = new ArrayList<>();
                j = countButton;
            } else if (i == 0) {
                buttonList.add(new InlineKeyboardButton().setText(String.valueOf(i)).setCallbackData(list.get(i)));
                doubleButtonList.add(buttonList);
                break;
            }
            buttonList.add(new InlineKeyboardButton().setText(String.valueOf(i)).setCallbackData(list.get(i)));
            j--;
        }
        return new InlineKeyboardMarkup(doubleButtonList);
    }

    public static ReplyKeyboardMarkup getReplyKeyboard(List<String> list, int countButton) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyBoard = new ArrayList<>();
        KeyboardRow keyboardRow = new KeyboardRow();

        int j = countButton;
        for (int i = list.size() - 1; i >= 0; i--) {
            if (j == 0) {
                keyBoard.add(keyboardRow);
                keyboardRow = new KeyboardRow();
                j = countButton;
            } else if (i == 0) {
                keyboardRow.add(list.get(i));
                keyBoard.add(keyboardRow);
                break;
            }
            keyboardRow.add(list.get(i));
            j--;
        }
        return replyKeyboardMarkup.setKeyboard(keyBoard);
    }

}
