package ru.youpromocodebot.model.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import ru.youpromocodebot.model.Coupon;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CouponsToUser {

    List<Coupon> coupons;
}
