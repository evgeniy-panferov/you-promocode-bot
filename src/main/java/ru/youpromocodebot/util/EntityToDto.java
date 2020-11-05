package ru.youpromocodebot.util;

import org.springframework.stereotype.Component;
import ru.youpromocodebot.model.Coupon;
import ru.youpromocodebot.model.dto.admitad.Coupons;
import ru.youpromocodebot.model.dto.admitad.Programs;
import ru.youpromocodebot.model.dto.user.CouponToUser;
import ru.youpromocodebot.model.dto.user.ProgramToUser;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EntityToDto {

    public static List<ProgramToUser> convertProgramToDto(Programs programs) {
        return programs.getPrograms().stream().map(program -> {
            ProgramToUser programToUser = new ProgramToUser();
            programToUser.setId(program.getId());
            programToUser.setName(program.getName());
            programToUser.setImageUrl(program.getImageUrl());
            return programToUser;
        }).collect(Collectors.toList());
    }

    public static List<CouponToUser> convertCouponToDto(Coupons coupons) {
        return coupons.getCoupons().stream().map(coupon -> {
            CouponToUser couponToUser = new CouponToUser();
            couponToUser.setName(coupon.getCampaign().getName());
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
            return couponToUser;
        }).collect(Collectors.toList());
    }

    public static CouponToUser convertCouponToUser(Coupon coupon){
        CouponToUser couponToUser = new CouponToUser();
        couponToUser.setName(coupon.getCampaign().getName());
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
        return couponToUser;
    }
}
