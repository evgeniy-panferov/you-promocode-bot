package ru.youpromocodebot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;

@Getter
@Setter
@MappedSuperclass
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class BaseEntity {

    protected BaseEntity() {
    }

    protected BaseEntity(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    protected Integer id;

    @Column(name = "name")
    @JsonProperty
    private String name;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ":" + id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !getClass().equals(Hibernate.getClass(o))) {
            return false;
        }
        BaseEntity that = (BaseEntity) o;
        return (id != null && id.equals(that.id));
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id;
    }
}
