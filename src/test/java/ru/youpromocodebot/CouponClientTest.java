package ru.youpromocodebot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.youpromocodebot.client.CouponsApi;
import ru.youpromocodebot.model.Coupon;
import ru.youpromocodebot.model.dto.user.CouponToUser;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;

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
    }
}
