package ru.youpromocodebot.model.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.youpromocodebot.model.Category;
import ru.youpromocodebot.model.Type;

import java.time.LocalDateTime;
import java.util.List;

public class CouponToUser {

    private String status;

    private String description;

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
