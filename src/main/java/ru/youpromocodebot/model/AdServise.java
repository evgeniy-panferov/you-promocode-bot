package ru.youpromocodebot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdServise extends BaseEntity {

    private String url;

    @JsonProperty(value = "logo")
    private String logoUrl;

    @JsonProperty(value = "allowed_referrers")
    private String allowedReferrers;

}
