package ru.youpromocodebot.api.handlers;

import com.vdurmont.emoji.EmojiParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import ru.youpromocodebot.api.MessageService;
import ru.youpromocodebot.api.YouPromocodeBot;
import ru.youpromocodebot.model.dto.user.CouponToUser;
import ru.youpromocodebot.service.CouponsService;
import ru.youpromocodebot.service.MessagesGenerator;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class CouponsCommandHandler implements CommandHandler {

    private final CouponsService couponsService;
    private final MessagesGenerator messagesGenerator;
    private final YouPromocodeBot youPromocodeBot;
    private final MessageService messageService;

    public CouponsCommandHandler(CouponsService couponsService, MessagesGenerator messagesGenerator,
                                 @Lazy YouPromocodeBot youPromocodeBot, MessageService messageService) {
        this.couponsService = couponsService;
        this.messagesGenerator = messagesGenerator;
        this.youPromocodeBot = youPromocodeBot;
        this.messageService = messageService;
    }

    public SendMessage resolveMatchCommand(Long chatId, String... text) {
        log.info("CouponsCommandHandler findMatchCommand - text {}, chatId {}", text, chatId);
        if (text[0].equalsIgnoreCase(messageService.getMessage("reply.button.actionList"))) {
            youPromocodeBot.sendMessage(new SendMessage(chatId, EmojiParser.parseToUnicode(":stopwatch:") +"Секунду, данные загружаются"));
            List<CouponToUser> couponsToUsers = couponsService.getForPartnerShipsProgram(text[1], Boolean.parseBoolean(text[2]));

            couponsToUsers
                    .stream()
                    .filter(couponToUser -> couponToUser.getRegions().contains("RU"))
                    .forEach(couponToUser -> {
                        SendPhoto sendPhoto = getSendPhoto(chatId, couponToUser);
                        youPromocodeBot.sendPhoto(sendPhoto);
                    });

            String message = couponsToUsers.isEmpty() ? "Акции не найдены" :
                    messageService.getMessageSmile("smile.creditCard", "reply.loaded.coupons.actions");
            return new SendMessage(chatId, message);
        }
        return null;
    }

    private SendPhoto getSendPhoto(Long chatId, CouponToUser couponToUser) {
        Map<String, String> messageMap = new LinkedHashMap<>();
        messageMap.put("smile.seek|reply.coupon.name", couponToUser.getName());
        messageMap.put("smile.check|reply.coupon.status", couponToUser.getStatus());
        messageMap.put("smile.memo|reply.coupon.description", couponToUser.getDescription());
        messageMap.put("smile.creditCard|reply.coupon.discount", couponToUser.getDiscount());
        messageMap.put("smile.check|reply.coupon.species", couponToUser.getSpecies());
        messageMap.put("smile.memo|reply.coupon.shortName", couponToUser.getShortName());
        messageMap.put("smile.watch|reply.coupon.dateStart", couponToUser.getDateStart());
        messageMap.put("smile.watch|reply.coupon.dateEnd", couponToUser.getDateEnd());
        messageMap.put("smile.link|reply.coupon.link", couponToUser.getGotoLink());
        messageMap.put("smile.check|reply.coupon.promocode", couponToUser.getPromocode());
        return messagesGenerator.createPhotoMessageToUser(chatId, getMessage(messageMap), couponToUser.getImageUrl(), couponToUser.getIsDatabaseEntity());
    }

    private String getMessage(Map<String, String> messageMap) {
        return messageMap
                .keySet()
                .stream()
                .filter(key -> !messageMap.get(key).equalsIgnoreCase(""))
                .map(key -> {
                    String[] split = key.split("\\|");
                    return messageService.getMessageSmile(split[0], split[1], messageMap.get(key));
                })
                .reduce((acc, string) -> acc + string)
                .orElse("Информация о скидке не заполнена");
    }
}
