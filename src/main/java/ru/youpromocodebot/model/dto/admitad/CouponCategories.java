package ru.youpromocodebot.model.dto.admitad;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ru.youpromocodebot.model.Category;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CouponCategories {

    @JsonProperty(value = "results")
    List<Category> categories;

}
