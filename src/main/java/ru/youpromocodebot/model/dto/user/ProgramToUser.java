package ru.youpromocodebot.model.dto.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode
public class ProgramToUser {

    private Integer id;

    private String name;

    private String imageUrl;

    private String connectionStatus;

}
