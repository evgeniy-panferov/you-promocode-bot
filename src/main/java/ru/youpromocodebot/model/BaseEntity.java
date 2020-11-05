package ru.youpromocodebot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class BaseEntity {
    @JsonProperty
    private Integer id;
    @JsonProperty
    private String name;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name == null ? "" : name;
    }
}
