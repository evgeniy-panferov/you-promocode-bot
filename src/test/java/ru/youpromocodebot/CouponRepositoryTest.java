package ru.youpromocodebot;


import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.youpromocodebot.dao.CouponDaoImpl;
import ru.youpromocodebot.model.Coupon;
import ru.youpromocodebot.model.dto.admitad.Coupons;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static ru.youpromocodebot.TestData.COUPONS_DB;
import static ru.youpromocodebot.TestData.getNewCouponDbEntity;

class CouponRepositoryTest extends AbstractTest {

    @Autowired
    private CouponDaoImpl couponDAOImpl;

    @Test
    void findAll() {
        Coupons coupons = couponDAOImpl.findAll();
        MatcherAssert.assertThat(coupons.getCoupons(), containsInAnyOrder(COUPONS_DB.toArray()));
    }

    @Test
    @Transactional
    void save() {
        Coupon coupon = couponDAOImpl.save(getNewCouponDbEntity());
        Coupon sample = getNewCouponDbEntity();
        sample.setId(coupon.getId());
        assertThat(sample, equalTo(coupon));
    }

    @Test
    @Transactional
    void delete() {
        assertThat(couponDAOImpl.delete(0), equalTo(1));
    }
}
