package ru.youpromocodebot.dao;

import ru.youpromocodebot.model.dto.user.ProgramToUser;

import java.util.List;

public interface PartnershipsProgramsDao {
    List<ProgramToUser> findAll();
}
