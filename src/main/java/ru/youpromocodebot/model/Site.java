package ru.youpromocodebot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Site extends BaseEntity {

    private String status;

    private String kind;

    @JsonProperty(value = "is_old")
    private Boolean isOld;

    @JsonProperty(value = "verification_code")
    private String verificationCode;

    private AdServise adService;

    @JsonProperty(value = "site_url")
    private String siteUrl;

    @JsonProperty(value = "validation_passed")
    private Boolean validationPassed;

    @JsonProperty(value = "is_lite")
    private Boolean isLite;

    @JsonProperty(value = "account_id")
    private String accountId;

    @JsonProperty(value = "creation_date")
    private LocalDateTime creationDate;

}
