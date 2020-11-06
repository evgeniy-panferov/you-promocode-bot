package ru.youpromocodebot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Action extends BaseEntity {

    private String type;

    @JsonProperty(value = "payment_size")
    private String paymentSize;

    @JsonProperty(value = "hold_time")
    private Integer holdTime;

    @JsonProperty(value = "hold_size")
    private Integer holdSize;

    private List<Tariff> tariffs;

}


