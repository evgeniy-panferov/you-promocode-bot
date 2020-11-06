package ru.youpromocodebot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Feed {

    private String name;

    @JsonProperty(value = "admitad_last_update")
    private String admitadLastUpdate;

    @JsonProperty(value = "advertiser_last_update")
    private String advertiserLastUpdate;

    @JsonProperty(value = "csv_link")
    private String csvLink;

    @JsonProperty(value = "xml_link")
    private String xmlLink;
}
