package ru.youpromocodebot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Rate {

    private Integer id;

    @JsonProperty(value = "price_s")
    private Float priceS;

    @JsonProperty(value = "tariff_id")
    private Integer tariffId;

    private String country;

    @JsonProperty(value = "date_s")
    private String dateS;

    @JsonProperty(value = "is_percentage")
    private Boolean isPercentage;

    private Float size;
}
