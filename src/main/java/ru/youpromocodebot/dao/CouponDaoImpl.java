package ru.youpromocodebot.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.youpromocodebot.model.Coupon;
import ru.youpromocodebot.model.dto.admitad.Coupons;
import ru.youpromocodebot.model.dto.user.CouponToUser;

import java.util.List;

import static ru.youpromocodebot.util.EntityToDto.convertCouponToDto;
import static ru.youpromocodebot.util.EntityToDto.convertCouponToUser;

@Repository
public class CouponDaoImpl implements CouponDao {

    private static final Logger log = LoggerFactory.getLogger(CouponDaoImpl.class);

    private final CrudCouponRepository crudCouponRepository;

    public CouponDaoImpl(CrudCouponRepository crudCouponRepository) {
        this.crudCouponRepository = crudCouponRepository;
    }

    @Override
    public Coupons findAll() {
        Coupons coupons = new Coupons();
        coupons.setCoupons(crudCouponRepository.findAll());
        return coupons;
    }

    @Override
    public CouponToUser getForId(String id) {
        log.info("CouponDAOImpl get id - {}", id);
        return convertCouponToUser(crudCouponRepository.getForId(Integer.parseInt(id)));
    }

    @Override
    public List<CouponToUser> getForPartnershipsProgram(String id) {
        return convertCouponToDto(crudCouponRepository.getForPartnershipsProgram(Integer.parseInt(id)));
    }

    public Coupon save(Coupon coupon) {
        log.info("CouponDAOImpl save coupon - {}", coupon);
        return crudCouponRepository.save(coupon);
    }

    public int delete(int id) {
        return crudCouponRepository.deleteCouponById(id);
    }
}
