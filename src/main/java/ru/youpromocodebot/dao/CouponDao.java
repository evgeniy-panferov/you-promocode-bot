package ru.youpromocodebot.dao;

import ru.youpromocodebot.model.dto.admitad.Coupons;
import ru.youpromocodebot.model.dto.user.CouponToUser;

import java.util.List;

public interface CouponDao {

    CouponToUser getForId(String id);

    List<CouponToUser> getForPartnershipsProgram(String id);

    Coupons findAll();
}
