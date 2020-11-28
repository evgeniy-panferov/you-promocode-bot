package ru.youpromocodebot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.youpromocodebot.client.CouponsApi;
import ru.youpromocodebot.dao.CouponDaoImpl;
import ru.youpromocodebot.model.dto.admitad.Coupons;
import ru.youpromocodebot.model.dto.user.CouponToUser;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CouponsService {

    private final CouponsApi couponsApi;
    private final CouponDaoImpl couponDaoImpl;

    public Coupons findAll() {
        log.info("CouponsService getCoupons");
        return couponsApi.findAll();
    }

    public List<CouponToUser> getForPartnerShipsProgram(String id, boolean isDatabaseEntity) {
        log.info("CouponsService getCouponsForPartnershipsProgram id - {}", id);
        return isDatabaseEntity ? couponDaoImpl.getForPartnershipsProgram(id) : couponsApi.getForPartnershipsProgram(id);
    }

    public CouponToUser getForId(String id, boolean isDatabaseEntity) {
        log.info("CouponsService getCouponForId id-{}", id);
        return isDatabaseEntity ? couponDaoImpl.getForId(id) : couponsApi.getForId(id);
    }
}
