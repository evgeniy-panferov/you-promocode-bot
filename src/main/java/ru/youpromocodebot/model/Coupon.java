package ru.youpromocodebot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import ru.youpromocodebot.client.JsonDateSerializer;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "coupon")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Coupon extends BaseEntity {

    public Coupon() {

    }

    public Coupon(String name, String status, Campaign campaign, String description, List<String> regions, String discount,
                  String species, String promocode, String framesetLink, String gotoLink, String shortName,
                  LocalDateTime dateStart, LocalDateTime dateEnd, String imageUrl, Boolean isDatabaseEntity) {

        this(null, name, status, campaign, description, regions, discount, species, promocode, framesetLink, gotoLink,
                shortName, dateStart, dateEnd, imageUrl, isDatabaseEntity);

    }

    public Coupon(Integer id, String name, String status, Campaign campaign, String description, List<String> regions, String discount,
                  String species, String promocode, String framesetLink, String gotoLink, String shortName,
                  LocalDateTime dateStart, LocalDateTime dateEnd, String imageUrl, Boolean isDatabaseEntity) {
        super(id, name);
        this.status = status;
        this.campaign = campaign;
        this.description = description;
        this.regions = regions;
        this.discount = discount;
        this.species = species;
        this.promocode = promocode;
        this.framesetLink = framesetLink;
        this.gotoLink = gotoLink;
        this.shortName = shortName;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.imageUrl = imageUrl;
        this.isDatabaseEntity = isDatabaseEntity;
    }

    @Column
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campaign_id")
    private Campaign campaign;

    @Column
    private String description;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "Region",
            joinColumns = @JoinColumn(name = "coupon_id")
    )
    @Column(name = "region")
    @BatchSize(size = 50)
    private List<String> regions;

    @Column
    private String discount;

    @Column
    private String species;

    @Column
    private String promocode;

    @Column(name = "frameset_link")
    @JsonProperty(value = "frameset_link")
    private String framesetLink;

    @Column(name = "goto_link")
    @JsonProperty(value = "goto_link")
    private String gotoLink;

    @Column(name = "short_name")
    @JsonProperty(value = "short_name")
    private String shortName;

    @Column(name = "date_start")
    @JsonProperty(value = "date_start")
    @JsonDeserialize(using = JsonDateSerializer.class)
    private LocalDateTime dateStart;

    @Column(name = "date_end")
    @JsonProperty(value = "date_end")
    @JsonDeserialize(using = JsonDateSerializer.class)
    private LocalDateTime dateEnd;

    @Column(name = "image_url")
    @JsonProperty(value = "image")
    private String imageUrl;

    @Column(name = "is_database_entity")
    private Boolean isDatabaseEntity = false;

    @Override
    public String toString() {
        return "Coupon{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", framesetLink='" + framesetLink + '\'' +
                ", gotoLink='" + gotoLink + '\'' +
                ", shortName='" + shortName + '\'' +
                '}';
    }
}
