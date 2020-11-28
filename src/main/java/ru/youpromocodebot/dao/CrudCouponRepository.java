package ru.youpromocodebot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.youpromocodebot.model.Coupon;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudCouponRepository extends JpaRepository<Coupon, Integer> {

    @Query("SELECT c from Coupon c where c.id=:id")
    Coupon getForId(@Param("id") int id);

    @Query("SELECT c from Coupon c where c.campaign.id=:campaignId")
    List<Coupon> getForPartnershipsProgram(@Param("campaignId") int campaignId);

    int deleteCouponById(int id);

}
