package ru.youpromocodebot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@DiscriminatorValue(value = "Campaign")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Campaign extends BaseEntity {

    @Column(name = "image_url")
    @JsonProperty(value = "image")
    private String imageUrl;

    @Column(name = "connection_status")
    @JsonProperty(value = "connection_status")
    private String connectionStatus;

    @Column(name = "is_database_entity")
    private Boolean isDatabaseEntity = false;

    @OneToMany(mappedBy = "campaign")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Coupon> coupons;

}
