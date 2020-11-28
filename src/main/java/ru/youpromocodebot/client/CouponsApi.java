package ru.youpromocodebot.client;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import ru.youpromocodebot.dao.CouponDao;
import ru.youpromocodebot.model.Coupon;
import ru.youpromocodebot.model.dto.admitad.Coupons;
import ru.youpromocodebot.model.dto.user.CouponToUser;

import java.text.MessageFormat;
import java.util.List;

import static ru.youpromocodebot.util.EntityToDto.convertCouponToDto;
import static ru.youpromocodebot.util.EntityToDto.convertCouponToUser;

@Controller
@RequiredArgsConstructor
public class CouponsApi implements CouponDao {

    private static final Logger log = LoggerFactory.getLogger(CouponsApi.class);

    @Value("${websiteId}")
    private String websiteId;

    @Value("${limit}")
    private String limit;

    private static final String COUPONS_URL = "https://api.admitad.com/coupons/";

    private static final String COUPONS_FOR_ID_AND_PARTNERSHIPS_ID = "https://api.admitad.com/coupons/{0}/website/{1}/";

    private final AdmitadConnection admitadConnection;


    public Coupons findAll() {
        log.info("CouponsApi getCoupons");
        return admitadConnection.getEntity(COUPONS_URL, HttpMethod.GET, Coupons.class);
    }

    @Cacheable(value = "couponsForPartnerShipsProgram", key = "#id")
    public List<CouponToUser> getForPartnershipsProgram(String id) {
        log.info("CouponsService getCouponsForPartnerShipsProgram id-{}, limit-{}", id, limit);
        MultiValueMap<String, String> query = new LinkedMultiValueMap<>();
        query.add("campaign", id);
        query.add("limit", limit);
        return convertCouponToDto(admitadConnection.getEntity(COUPONS_URL, HttpMethod.GET, Coupons.class, query));
    }

    @Cacheable(value = "couponToUser", key = "#id")
    public CouponToUser getForId(String id) {
        log.info("CouponsService getCouponForId id-{}", id);
        String formatUrl = MessageFormat.format(COUPONS_FOR_ID_AND_PARTNERSHIPS_ID, id, websiteId);
        return convertCouponToUser(admitadConnection.getEntity(formatUrl, HttpMethod.GET, Coupon.class));
    }
}