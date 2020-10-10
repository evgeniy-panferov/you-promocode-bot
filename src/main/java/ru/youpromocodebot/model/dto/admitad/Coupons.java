package ru.youpromocodebot.model.dto.admitad;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ru.youpromocodebot.model.Coupon;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Coupons {

    @JsonProperty(value = "results")
    List<Coupon> coupons;
}
