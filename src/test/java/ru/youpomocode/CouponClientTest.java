package ru.youpomocode;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.youpromocodebot.client.CouponsApi;
import ru.youpromocodebot.model.Coupon;
import ru.youpromocodebot.model.dto.user.CouponToUser;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


class CouponClientTest extends AbstractTest {

    @Autowired
    private CouponsApi couponsApi;

    @Test
    void findAll() {
        List<Coupon> coupons = couponsApi.findAll().getCoupons();
        assertThat(coupons, not(empty()));
    }

    @Test
    void getForPartnerShipsProgram() {
        List<CouponToUser> coupons = couponsApi.getForPartnershipsProgram(String.valueOf(3081));
        assertThat(coupons, not(empty()));
        assertThat(coupons.get(0).getName(), equalTo("Hoff"));
    }

    @Test
    void getCouponForId() {
        CouponToUser couponForId = couponsApi.getForId(String.valueOf(336210));
        assertThat(couponForId.getShortName(), equalTo("Скидки на товары для дома"));
    }

}
