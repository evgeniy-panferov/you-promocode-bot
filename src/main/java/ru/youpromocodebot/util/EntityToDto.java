package ru.youpromocodebot.util;

import ru.youpromocodebot.model.dto.admitad.Programs;
import ru.youpromocodebot.model.dto.user.ProgramToUser;

import java.util.List;
import java.util.stream.Collectors;

public class EntityToDto {

    public static List<ProgramToUser> convertProgramToDto(Programs programs) {
        return programs.getPrograms().stream().map(program -> {
            ProgramToUser programToUser = new ProgramToUser();
            programToUser.setId(program.getId());
            programToUser.setName(program.getName());
            programToUser.setImageUrl(program.getImageUrl());
            return programToUser;
        }).collect(Collectors.toList());
    }

}
