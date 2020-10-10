package ru.youpromocodebot.model.dto.admitad;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import ru.youpromocodebot.model.Site;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Sites {

   private List<Site> sites;


}
