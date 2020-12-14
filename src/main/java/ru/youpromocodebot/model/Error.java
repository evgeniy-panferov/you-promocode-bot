package ru.youpromocodebot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Error {

    @JsonProperty(value = "status_code")
    private Integer statusCode;

    @JsonProperty(value = "error_description")
    private String errorDescription;

    private String error;

}
