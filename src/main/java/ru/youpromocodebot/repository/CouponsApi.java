package ru.youpromocodebot.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import ru.youpromocodebot.client.AdmitadConnection;
import ru.youpromocodebot.model.Coupon;
import ru.youpromocodebot.model.dto.admitad.CouponCategories;
import ru.youpromocodebot.model.dto.admitad.Coupons;
import ru.youpromocodebot.model.dto.user.CouponToUser;
import ru.youpromocodebot.util.EntityToDto;

import java.text.MessageFormat;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CouponsApi {

    @Value("${websiteId}")
    private String websiteId;

    @Value("${limit}")
    private String limit;

    private static final String COUPONS_URL = "https://api.admitad.com/coupons/";

    private static final String CATEGORIES_URL = "https://api.admitad.com/coupons/categories/";

    private static final String COUPONS_URL_FOR_SITES = "https://api.admitad.com/coupons/website/";

    private static final String COUPONS_FOR_ID_AND_PARTNERSHIPS_ID = "https://api.admitad.com/coupons/{0}/website/{1}/";

    private final AdmitadConnection admitadConnection;

    public CouponsApi getCoupons() {
        log.info("CouponsApi getCoupons");
        return admitadConnection.getEntity(COUPONS_URL, HttpMethod.GET, CouponsApi.class);
    }

    public CouponsApi getCouponsForSites(String id) {
        log.info("CouponsApi getCouponsForSites id - {}", id);
        return admitadConnection.getEntity(COUPONS_URL_FOR_SITES, HttpMethod.GET, CouponsApi.class, id);
    }

    public CouponCategories getCategoriesCoupons() {
        log.info("CouponsApi getCategories");
        return admitadConnection.getEntity(CATEGORIES_URL, HttpMethod.GET, CouponCategories.class);
    }

    public List<CouponToUser> getCouponsForPartnerShipsProgram(String id) {
        log.info("CouponsService getCouponsForPartnerShipsProgram id-{}, limit-{}", id, limit);
        MultiValueMap<String, String> query = new LinkedMultiValueMap<>();
        query.add("campaign", id);
        query.add("limit", limit);
        return EntityToDto.convertCouponToDto(admitadConnection.getEntity(COUPONS_URL, HttpMethod.GET, Coupons.class, query));
    }

    public CouponToUser getCouponForId(String id) {
        log.info("CouponsService getCouponForId id-{}", id);
        String formatUrl = MessageFormat.format(COUPONS_FOR_ID_AND_PARTNERSHIPS_ID, id, websiteId);
        return EntityToDto.convertCouponToUser(admitadConnection.getEntity(formatUrl, HttpMethod.GET, Coupon.class));
    }
}