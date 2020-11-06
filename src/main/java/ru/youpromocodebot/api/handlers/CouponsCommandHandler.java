package ru.youpromocodebot.api.handlers;

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

import java.util.HashMap;
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
            List<CouponToUser> collect = couponsService.getCouponsForPartnershipsProgram(text[1]);

            collect.stream()
                    .filter(couponToUser -> couponToUser.getRegions().contains("RU"))
                    .forEach(couponToUser -> {
                        CouponToUser couponForIdForWebsite = couponsService.getCouponForIdForWebsite(String.valueOf(couponToUser.getId()));

                        Map<String, String> messageMap = new HashMap<>();
                        messageMap.put("reply.coupon.name", couponForIdForWebsite.getName());
                        messageMap.put("reply.coupon.status", couponForIdForWebsite.getStatus());
                        messageMap.put("reply.coupon.description", couponForIdForWebsite.getDescription());
                        messageMap.put("reply.coupon.discount", couponForIdForWebsite.getDiscount());
                        messageMap.put("reply.coupon.species", couponForIdForWebsite.getSpecies());
                        messageMap.put("reply.coupon.shortName", couponForIdForWebsite.getShortName());
                        messageMap.put("reply.coupon.dateStart", couponForIdForWebsite.getDateStart());
                        messageMap.put("reply.coupon.dateEnd", couponForIdForWebsite.getDateEnd());
                        messageMap.put("reply.coupon.link", couponForIdForWebsite.getGotoLink());
                        messageMap.put("reply.coupon.promocode", couponForIdForWebsite.getPromocode());

                        SendPhoto sendPhoto = messagesGenerator.createPhotoMessageToUser(chatId, getMessage(messageMap), couponForIdForWebsite.getImageUrl());
                        youPromocodeBot.sendPhoto(sendPhoto);
                    });

            String message = collect.isEmpty() ? "Акции не найдены" :
                    messageService.getMessage("reply.loaded.coupons.actions", collect.get(0).getName());
            return new SendMessage(chatId, message);
        }
        return null;
    }

    private String getMessage(Map<String, String> messageMap) {
        return messageMap.keySet()
                .stream()
                .filter(key -> !messageMap.get(key).equalsIgnoreCase(""))
                .map(key -> messageService.getMessage(key, messageMap.get(key)))
                .reduce((acc, string) -> acc + string)
                .orElse("Информация о скидке не заполнена");

    }
}
