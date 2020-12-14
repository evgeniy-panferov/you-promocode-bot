package ru.youpromocodebot.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.youpromocodebot.client.CouponsApi;
import ru.youpromocodebot.dao.CouponDaoImpl;
import ru.youpromocodebot.model.dto.admitad.Coupons;
import ru.youpromocodebot.model.dto.user.CouponToUser;

import java.util.List;

import static ru.youpromocodebot.util.EntityToDto.convertCouponsToDtoWeb;


@Service
@RequiredArgsConstructor
public class CouponsService {

    private final CouponsApi couponsApi;
    private final CouponDaoImpl couponDaoImpl;

    private static final Logger log = LoggerFactory.getLogger(CouponsService.class);

    public Coupons findAll() {
        log.info("CouponsService getCoupons");
        return couponsApi.findAll();
    }

    @Cacheable(value = "couponsForPartnerShipsProgram")
    public List<CouponToUser> getForPartnerShipsProgram(String id, boolean isDatabaseEntity) {
        log.info("CouponsService getCouponsForPartnershipsProgram id - {}", id);
        return isDatabaseEntity ? convertCouponsToDtoWeb(couponDaoImpl.getForPartnershipsProgram(id)) : couponsApi.getForPartnershipsProgram(id);
    }
}
