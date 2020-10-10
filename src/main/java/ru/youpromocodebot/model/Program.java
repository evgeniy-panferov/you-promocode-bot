package ru.youpromocodebot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Program extends BaseEntity {

    @JsonProperty
    private String status;

    @JsonProperty
    private Float rating;

    @JsonProperty
    private String description;

    @JsonProperty
    private List<Traffic> traffics;

    @JsonProperty
    private List<Action> actions;

    @JsonProperty
    private List<Region> regions;

    @JsonProperty
    private String currency;

    @JsonProperty
    private Boolean geotargeting;

    @JsonProperty
    private Boolean connected;

    @JsonProperty
    private Float cr;

    @JsonProperty
    private Float ecpc;

    @JsonProperty
    private Float epc;

    @JsonProperty
    private List<Category> categories;

    @JsonProperty(value = "cr_trend")
    private Float crTrend;

    @JsonProperty(value = "ecpc_trend")
    private Float ecpcTrend;

    @JsonProperty(value = "epc_trend")
    private Float epcTrend;

    @JsonProperty(value = "action_type")
    private String actionType;

    @JsonProperty(value = "individual_terms")
    private Boolean individualTerms;

    @JsonProperty(value = "allow_deeplink")
    private Boolean allowDeepLink;

    @JsonProperty(value = "action_testing_limit")
    private Integer actionTestingLimit;

    @JsonProperty(value = "mobile_device_type")
    private String mobileDeviceType;

    @JsonProperty(value = "mobile_os_type")
    private String mobileOsType;

    @JsonProperty(value = "mobile_os")
    private String mobileOs;

    @JsonProperty(value = "action_countries")
    private List<String> actionCountries;

    @JsonProperty(value = "allow_actions_all_countries")
    private Boolean allowActionsAllCountries;

    @JsonProperty(value = "site_url")
    private String siteUrl;

    @JsonProperty(value = "coupon_iframe_denied")
    private Boolean couponIframeDenied;

    @JsonProperty(value = "image")
    private String imageUrl;
}
