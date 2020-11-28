package ru.youpromocodebot.model.dto.admitad;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.youpromocodebot.model.Coupon;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Coupons {

    @JsonProperty(value = "results")
    private List<Coupon> coupons;

}
