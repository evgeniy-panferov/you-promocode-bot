package ru.youpromocodebot.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.youpromocodebot.model.Coupon;
import ru.youpromocodebot.model.dto.admitad.Coupons;
import ru.youpromocodebot.model.dto.admitad.Programs;
import ru.youpromocodebot.model.dto.user.CouponToUser;
import ru.youpromocodebot.model.dto.user.ProgramToUser;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EntityToDto {

    private static final Logger log = LoggerFactory.getLogger(EntityToDto.class);

    public static List<ProgramToUser> convertProgramToDto(Programs programs) {
        log.info("EntityToDto convertProgramToDto programs-{}", programs);
        return programs.getCampaigns()
                .stream()
                .map(program -> {
                    ProgramToUser programToUser = new ProgramToUser();
                    programToUser.setImageUrl(program.getImageUrl());
                    programToUser.setId(program.getId());
                    programToUser.setName(program.getName());
                    programToUser.setConnectionStatus(program.getConnectionStatus());
                    programToUser.setIsDatabaseEntity(program.getIsDatabaseEntity());
                    return programToUser;
                }).collect(Collectors.toList());
    }

    public static CouponToUser convertCouponToUser(Coupon coupon) {
        log.info("EntityToDto convertCouponToUser coupon-{}", coupon);
        CouponToUser couponToUser = new CouponToUser();
        couponToUser.setName(coupon.getName());
        couponToUser.setStatus(coupon.getStatus());
        couponToUser.setDescription(coupon.getDescription());
        couponToUser.setDiscount(coupon.getDiscount());
        couponToUser.setSpecies(coupon.getSpecies());
        couponToUser.setShortName(coupon.getShortName());
        couponToUser.setDateStart(coupon.getDateStart());
        couponToUser.setDateEnd(coupon.getDateEnd());
        couponToUser.setImageUrl(coupon.getImageUrl());
        couponToUser.setRegions(coupon.getRegions());
        couponToUser.setId(coupon.getId());
        couponToUser.setFramesetLink(coupon.getFramesetLink());
        couponToUser.setGotoLink(coupon.getGotoLink());
        couponToUser.setPromocode(coupon.getPromocode());
        couponToUser.setIsDatabaseEntity(coupon.getIsDatabaseEntity());
        return couponToUser;
    }

    public static List<CouponToUser> convertCouponToDto(Coupons coupons) {
        log.info("EntityToDto convertCouponToDto coupons -{}", coupons);
        return convertCouponToDto(coupons.getCoupons());
    }

    public static List<CouponToUser> convertCouponToDto(List<Coupon> coupons) {
        log.info("EntityToDto convertCouponToDto coupons - {}", coupons);
        return coupons
                .stream()
                .map(EntityToDto::convertCouponToUser)
                .collect(Collectors.toList());
    }

    public static List<ProgramToUser> convertProgramToDtoWeb(List<ProgramToUser> programToUsers) {
        return programToUsers.stream()
                .map(programToUser -> {
                    programToUser.setImageUrl("https://tg-bot-site.herokuapp.com/api/image/" + programToUser.getImageUrl() + ".jpg");
                    return programToUser;
                }).collect(Collectors.toList());
    }

    public static List<CouponToUser> convertCouponsToDtoWeb(List<CouponToUser> couponToUsers) {
        return couponToUsers.stream()
                .map(couponToUser -> {
                    couponToUser.setImageUrl("https://tg-bot-site.herokuapp.com/api/image/" + couponToUser.getImageUrl() + ".jpg");
                    return couponToUser;
                }).collect(Collectors.toList());
    }
}
