package ru.youpromocodebot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.youpromocodebot.client.JsonDateSerializer;

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

    private List<String> regions;

    private String discount;

    private List<Type> types;

    private String species;

    private List<Category> categories;

    private Boolean exclusive;

    private String language;

    private String promocode;

    @JsonProperty(value = "frameset_link")
    private String framesetLink;

    @JsonProperty(value = "goto_link")
    private String gotoLink;

    @JsonProperty(value = "short_name")
    private String shortName;

    @JsonProperty(value = "date_start")
    @JsonDeserialize(using = JsonDateSerializer.class)
    private LocalDateTime dateStart;

    @JsonProperty(value = "date_end")
    @JsonDeserialize(using = JsonDateSerializer.class)
    private LocalDateTime dateEnd;

    @JsonProperty(value = "image")
    private String imageUrl;

}
