package ru.youpromocodebot.model.dto.user;

import lombok.*;
import ru.youpromocodebot.model.BaseEntity;
import ru.youpromocodebot.util.DateParse;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CouponToUser extends BaseEntity {

    private String status;

    private String description;

    private String discount;

    private String species;

    private String shortName;

    private LocalDateTime dateStart;

    private LocalDateTime dateEnd;

    private String imageUrl;

    private List<String> regions;

    private String promocode;

    private String framesetLink;

    private String gotoLink;

    private Boolean isDatabaseEntity;

    public String getStatus() {
        return status.equalsIgnoreCase("Active") ? "Активна" : "Не активна";
    }

    public String getDescription() {
        return description.equalsIgnoreCase("") ? "" : description;
    }

    public String getDiscount() {
        return discount == null ? "" : discount;
    }

    public String getSpecies() {
        return species.equalsIgnoreCase("promocode") ? "Промокод" : "Акция по ссылке";
    }

    public String getShortName() {
        return shortName == null ? "" : shortName;
    }

    @SneakyThrows
    public String getDateStart() {
        return dateStart != null ? DateParse.getStringDate(dateStart) : "";
    }

    @SneakyThrows
    public String getDateEnd() {
        return dateEnd != null ? DateParse.getStringDate(dateEnd) : "";
    }

    public String getPromocode() {
        return promocode == null ? "" : promocode;
    }

    public String getImageUrl() {
        return imageUrl == null ? "" : imageUrl;
    }

    public List<String> getRegions() {
        return regions == null ? Collections.emptyList() : regions;
    }

    public String getFramesetLink() {
        return framesetLink == null ? "" : framesetLink;
    }

    public String getGotoLink() {
        return gotoLink == null ? "" : gotoLink;
    }

    public Boolean isDatabaseEntity() {
        return isDatabaseEntity;
    }
}
