package ru.youpromocodebot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import ru.youpromocodebot.client.AdmitadConnectionApi;
import ru.youpromocodebot.model.dto.admitad.CouponCategories;
import ru.youpromocodebot.model.dto.admitad.Coupons;

@Slf4j
@Controller
public class CouponsApi {
    private static final String COUPONS_URL = "https://api.admitad.com/coupons/";

    private static final String CATEGORIES_URL = "https://api.admitad.com/coupons/categories/";

    private static final String COUPONS_URL_FOR_SITES = "https://api.admitad.com/coupons/website/";

    private final AdmitadConnectionApi admitadConnectionApi;

    public CouponsApi(AdmitadConnectionApi admitadConnectionApi) {
        this.admitadConnectionApi = admitadConnectionApi;
    }

    public Coupons getCoupons() {
        log.info("CouponsApi getCoupons");
        return admitadConnectionApi.getEntity(COUPONS_URL, HttpMethod.GET, Coupons.class);
    }

    public Coupons getCouponsForSites(String id) {
        log.info("CouponsApi getCouponsForSites id - {}", id);
        return admitadConnectionApi.getEntity(COUPONS_URL_FOR_SITES, HttpMethod.GET, Coupons.class, id);
    }

    public CouponCategories getCategoriesCoupons() {
        log.info("CouponsApi getCategories");
        return admitadConnectionApi.getEntity(CATEGORIES_URL, HttpMethod.GET, CouponCategories.class);
    }


}
