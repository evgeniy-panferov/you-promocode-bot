package ru.youpromocodebot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.youpromocodebot.client.JsonDateSerializer;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Program extends BaseEntity {

    @JsonProperty(value = "image")
    private String imageUrl;

    @JsonProperty(value = "connection_status")
    private String connectionStatus;

    @JsonIgnoreProperties
    private String status;

    @JsonIgnoreProperties
    private Float rating;

    @JsonIgnoreProperties
    private String description;

    @JsonIgnoreProperties
    private List<Traffic> traffics;

    @JsonIgnoreProperties
    private List<Action> actions;

    @JsonIgnoreProperties
    private List<Region> regions;

    @JsonIgnoreProperties
    private String currency;

    @JsonIgnoreProperties
    private Boolean connected;

    @JsonIgnoreProperties
    private Float cr;

    @JsonIgnoreProperties
    private Float ecpc;

    @JsonIgnoreProperties
    private Float epc;

    @JsonIgnoreProperties
    private Boolean exclusive;

    @JsonIgnoreProperties
    private Boolean denynewwms;

    @JsonIgnoreProperties
    private Boolean retag;

    @JsonIgnoreProperties
    private Boolean moderation;

    @JsonIgnoreProperties
    private List<Category> categories;

    @JsonIgnoreProperties
    @JsonProperty(value = "geotargeting")
    private Boolean geoTargeting;

    @JsonIgnoreProperties
    @JsonProperty(value = "site_url")
    private String siteUrl;

    @JsonIgnoreProperties
    @JsonProperty(value = "raw_description")
    private String rawDescription;

    @JsonIgnoreProperties
    @JsonProperty(value = "cr_trend")
    private Float crTrend;

    @JsonIgnoreProperties
    @JsonProperty(value = "ecpc_trend")
    private Float ecpcTrend;

    @JsonIgnoreProperties
    @JsonProperty(value = "epc_trend")
    private Float epcTrend;

    @JsonIgnoreProperties
    @JsonProperty(value = "action_type")
    private String actionType;

    @JsonIgnoreProperties
    @JsonProperty(value = "individual_terms")
    private Boolean individualTerms;

    @JsonIgnoreProperties
    @JsonProperty(value = "allow_deeplink")
    private Boolean allowDeepLink;

    @JsonIgnoreProperties
    @JsonProperty(value = "action_testing_limit")
    private Integer actionTestingLimit;

    @JsonIgnoreProperties
    @JsonProperty(value = "mobile_device_type")
    private String mobileDeviceType;

    @JsonIgnoreProperties
    @JsonProperty(value = "mobile_os_type")
    private String mobileOsType;

    @JsonIgnoreProperties
    @JsonProperty(value = "mobile_os")
    private String mobileOs;

    @JsonIgnoreProperties
    @JsonProperty(value = "action_countries")
    private List<String> actionCountries;

    @JsonIgnoreProperties
    @JsonProperty(value = "allow_actions_all_countries")
    private Boolean allowActionsAllCountries;

    @JsonIgnoreProperties
    @JsonProperty(value = "coupon_iframe_denied")
    private Boolean couponIframeDenied;

    @JsonIgnoreProperties
    @JsonProperty(value = "activation_date")
    @JsonDeserialize(using = JsonDateSerializer.class)
    private LocalDateTime activationDate;

    @JsonIgnoreProperties
    @JsonProperty(value = "modified_date")
    @JsonDeserialize(using = JsonDateSerializer.class)
    private LocalDateTime modifiedDate;

    @JsonIgnoreProperties
    @JsonProperty(value = "goto_cookie_lifetime")
    private Integer gotoCookieLifetime;

    @JsonIgnoreProperties
    @JsonProperty(value = "show_products_links")
    private Boolean showProductsLinks;

    @JsonIgnoreProperties
    @JsonProperty(value = "landing_code")
    private String landingCode;

    @JsonIgnoreProperties
    @JsonProperty(value = "landing_title")
    private String landingTitle;

    @JsonIgnoreProperties
    @JsonProperty(value = "max_hold_time")
    private String maxHoldTime;

    @JsonIgnoreProperties
    @JsonProperty(value = "avg_hold_time")
    private String avgHoldTime;

    @JsonIgnoreProperties
    @JsonProperty(value = "avg_money_transfer_time")
    private String avgMoneyTransferTime;

    @JsonIgnoreProperties
    @JsonProperty(value = "gotolink")
    private String goToLink;

    @JsonIgnoreProperties
    @JsonProperty(value = "products_xml_link")
    private String productsXmlLink;

    @JsonIgnoreProperties
    @JsonProperty(value = "products_csv_link")
    private String productsCsvLink;

    @JsonIgnoreProperties
    @JsonProperty(value = "feeds_info")
    private List<Feed> feedsInfo;

    @JsonIgnoreProperties
    @JsonProperty(value = "actions_detail")
    private List<Action> actionsDetail;

    @JsonIgnoreProperties
    @JsonProperty(value = "actions_limit")
    private Integer actionsLimit;

    @JsonIgnoreProperties
    @JsonProperty(value = "actions_limit_24")
    private Integer actionsLimit24;
}

