package ru.youpromocodebot.model.dto.admitad;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.youpromocodebot.model.Campaign;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Programs {

    @JsonProperty(value = "results")
    List<Campaign> campaigns;

    public Programs(List<Campaign> campaigns) {
        this.campaigns = campaigns;
    }
}
