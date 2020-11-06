package ru.youpromocodebot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import ru.youpromocodebot.model.dto.admitad.CouponCategories;
import ru.youpromocodebot.model.dto.user.CouponToUser;
import ru.youpromocodebot.repository.CouponsApi;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CouponsService {

    private final CouponsApi couponsApi;

    public CouponsApi getCoupons() {
        log.info("CouponsService getCoupons");
        return couponsApi.getCoupons();
    }

    public CouponsApi getCouponsForSites(String id) {
        log.info("CouponsService getCouponsForSites id - {}", id);
        return couponsApi.getCouponsForSites(id);
    }

    public CouponCategories getCategoriesCoupons() {
        log.info("CouponsService getCategories");
        return couponsApi.getCategoriesCoupons();
    }

    public List<CouponToUser> getCouponsForPartnershipsProgram(String id) {
        log.info("CouponsService getCouponsForPartnershipsProgram id - {}", id);
        return couponsApi.getCouponsForPartnerShipsProgram(id);
    }

    public CouponToUser getCouponForIdForWebsite(String id) {
        log.info("CouponsService getCouponForId id-{}", id);
        return couponsApi.getCouponForId(id);
    }
}
