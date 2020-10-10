package ru.youpromocodebot.view;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.youpromocodebot.service.PartnershipsProgramsApi;
import ru.youpromocodebot.state.State;
import ru.youpromocodebot.state.StateView;
import ru.youpromocodebot.util.FieldsClassUtil;

import static ru.youpromocodebot.util.KeyboardsUtil.getInlineKeyboard;
import static ru.youpromocodebot.util.KeyboardsUtil.getReplyKeyboard;
import static ru.youpromocodebot.util.MessagesGeneratorUtil.createCallbackQueryToUSer;
import static ru.youpromocodebot.util.MessagesGeneratorUtil.createTextMessageToUSer;


@Slf4j
@Component
public class TelegramView {

    private PartnershipsProgramsApi partnershipsProgramsApi;

    public TelegramView(PartnershipsProgramsApi partnershipsProgramsApi) {
        this.partnershipsProgramsApi = partnershipsProgramsApi;
    }

    public BotApiMethod updateHandler(Update update) {
        log.info("TelegramView updateHandler update {}", update);

        if (update.hasMessage() && update.getMessage().hasText()) {
            return inputTextHandler(update);
        }

        if (update.hasCallbackQuery()) {
            return inputCallbackQueryHandler(update);
        }
        return createTextMessageToUSer(update, "Что-то пошло не так");
    }

    private SendMessage inputTextHandler(Update update) {
        log.info("inputTextHandler chatId - {}, message - {}", update.getMessage().getChatId(), update.getMessage());
        String inputText = update.getMessage().getText();
        SendMessage sendMessage = createTextMessageToUSer(update, "Выбирай команду:");
        sendMessage.setReplyMarkup(getReplyKeyboard(FieldsClassUtil.getCommandsList(StateView.class), 3));
        switch (inputText) {
            case "/start" -> {
                sendMessage = createTextMessageToUSer(update, "Привет.\nЭто бот который ищет выгодные предложения.");
                sendMessage.setReplyMarkup(getReplyKeyboard(FieldsClassUtil.getCommandsList(StateView.class), 3));
            }
            case StateView.COMMAND_LIST -> {
                sendMessage.setText(FieldsClassUtil.getCommands(State.class).toString());
                sendMessage.setReplyMarkup(getInlineKeyboard(FieldsClassUtil.getCommandsList(State.class), 3));
            }
        }
        return sendMessage;
    }

    private SendMessage inputCallbackQueryHandler(Update update) {
        log.info("TelegramView inputCallbackQueryHandler chatId {}, update message{}", update.getCallbackQuery().getMessage().getChatId(), update.getCallbackQuery().getMessage());
        SendMessage sendMessage = new SendMessage();
        Long chatId = update.getCallbackQuery().getMessage().getChatId();
        String data = update.getCallbackQuery().getData();

        switch (data) {
            case "/start" -> {
                sendMessage = createCallbackQueryToUSer(update, "Привет.\nЭто бот который ищет выгодные предложения.");
            }
            case State.COMMAND_LIST -> {
                sendMessage.setText(FieldsClassUtil.getCommands(State.class).toString());
                sendMessage.setReplyMarkup(getInlineKeyboard(FieldsClassUtil.getCommandsList(State.class), 3));
            }
            case State.PROGRAM_LIST -> {
                sendMessage.setText(partnershipsProgramsApi.getProgramsFromSites().toString());
                sendMessage.setReplyMarkup(getInlineKeyboard(FieldsClassUtil.getCommandsList(State.class), 3));
            }
            default -> {
                sendMessage.setText(FieldsClassUtil.getCommands(State.class)
                        .insert(0, "Команда не найдена.\nСписок доступных:\n")
                        .toString());
                sendMessage.setReplyMarkup(getInlineKeyboard(FieldsClassUtil.getCommandsList(State.class), 3));
            }
        }
        sendMessage.setChatId(chatId);
        return sendMessage;
    }


}
