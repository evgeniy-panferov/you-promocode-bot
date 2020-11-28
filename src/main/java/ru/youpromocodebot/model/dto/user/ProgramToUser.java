package ru.youpromocodebot.model.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.youpromocodebot.model.BaseEntity;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ProgramToUser extends BaseEntity {

    public ProgramToUser(Integer id, String name, String imageUrl, String connectionStatus, Boolean isDatabaseEntity) {
        super(id, name);
        this.imageUrl = imageUrl;
        this.connectionStatus = connectionStatus;
        this.isDatabaseEntity = isDatabaseEntity;
    }

    private String imageUrl;

    private String connectionStatus;

    private Boolean isDatabaseEntity;

}
