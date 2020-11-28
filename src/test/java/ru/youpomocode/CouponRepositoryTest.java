package ru.youpomocode;


import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.transaction.annotation.Transactional;
import ru.youpomocode.config.TestDataBaseConfig;
import ru.youpromocodebot.config.AppConfig;
import ru.youpromocodebot.dao.CouponDaoImpl;
import ru.youpromocodebot.model.Coupon;
import ru.youpromocodebot.model.dto.admitad.Coupons;
import ru.youpromocodebot.model.dto.user.CouponToUser;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static ru.youpomocode.TestData.*;
import static ru.youpromocodebot.util.EntityToDto.convertCouponToUser;


@ContextConfiguration(classes = {AppConfig.class, TestDataBaseConfig.class})
@Sql(scripts = {"classpath:db/db-init", "classpath:db/db-populate"}, config = @SqlConfig(dataSource = "dataSource",
        transactionManager = "transactionManager", encoding = "UTF-8"))
class CouponRepositoryTest extends AbstractTest {

    @Autowired
    private CouponDaoImpl couponDAOImpl;

    @Test
    void findAll() {
        Coupons coupons = couponDAOImpl.findAll();
        MatcherAssert.assertThat(coupons.getCoupons(), containsInAnyOrder(COUPONS_DB.toArray()));
    }

    @Test
    void getForId() {
        CouponToUser coupon = couponDAOImpl.getForId("0");
        CouponToUser coupon1 = couponDAOImpl.getForId("1");
        CouponToUser mockCoupon = convertCouponToUser(COUPON_TEST_DB_ID_0);
        CouponToUser mockCoupon1 = convertCouponToUser(COUPON_TEST_DB_ID_1);
        assertThat(coupon, equalTo(mockCoupon));
        assertThat(coupon1, equalTo(mockCoupon1));
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
