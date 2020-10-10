package ru.youpromocodebot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Coupon extends BaseEntity {

    private String status;

    private Float rating;

    private Campaign campaign;

    private String description;

    private List<Region> regions;

    private String discount;

    private List<Type> types;

    private String species;

    private List<Category> categories;

    private Boolean exclusive;

    @JsonProperty(value = "short_name")
    private String shortName;

    @JsonProperty(value = "date_start")
    private LocalDateTime dateStart;

    @JsonProperty(value = "date_end")
    private LocalDateTime dateEnd;

    @JsonProperty(value = "image")
    private String imageUrl;
}
