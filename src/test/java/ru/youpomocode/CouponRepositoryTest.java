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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static ru.youpomocode.TestData.COUPONS_DB;
import static ru.youpomocode.TestData.getNewCouponDbEntity;


@ContextConfiguration(classes = {AppConfig.class, TestDataBaseConfig.class})
@Sql(scripts = {"classpath:db/test-db-init", "classpath:db/test-db-populate"}, config = @SqlConfig(dataSource = "dataSource",
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
