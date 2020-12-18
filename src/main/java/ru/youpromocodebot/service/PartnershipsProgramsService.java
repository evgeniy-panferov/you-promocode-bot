package ru.youpromocodebot.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.youpromocodebot.client.PartnershipsProgramsApi;
import ru.youpromocodebot.dao.PartnershipsProgramsDaoImpl;
import ru.youpromocodebot.model.dto.user.ProgramToUser;

import java.util.List;

import static ru.youpromocodebot.util.EntityToDto.convertProgramToDtoWeb;

/**
 * Работа с партнерскими программами
 */

@Service
@AllArgsConstructor
public class PartnershipsProgramsService {

    private final PartnershipsProgramsApi partnershipsProgramsApi;
    private final PartnershipsProgramsDaoImpl partnershipsProgramsDaoImpl;
    private static final Logger log = LoggerFactory.getLogger(PartnershipsProgramsService.class);

    @Cacheable(value = "programsToUser")
    public List<ProgramToUser> getProgramsFromSites() {
        log.info("PartnershipsProgramsService getPartnershipsPrograms id");
        List<ProgramToUser> programsToUser = convertProgramToDtoWeb(partnershipsProgramsDaoImpl.findAll());
        programsToUser.addAll(partnershipsProgramsApi.getProgramsFromSites());
        return programsToUser;
    }
}
