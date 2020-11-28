package ru.youpromocodebot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import ru.youpromocodebot.client.PartnershipsProgramsApi;
import ru.youpromocodebot.dao.PartnershipsProgramsDaoImpl;
import ru.youpromocodebot.model.dto.user.ProgramToUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Работа с партнерскими программами
 */

@Slf4j
@Component
public class PartnershipsProgramsService {

    private final PartnershipsProgramsApi partnershipsProgramsApi;
    private final PartnershipsProgramsDaoImpl partnershipsProgramsDaoImpl;

    @Value("${websiteId}")
    private String websiteId;
    @Value("${limit}")
    private String limit;

    public PartnershipsProgramsService(PartnershipsProgramsApi partnershipsProgramsApi, PartnershipsProgramsDaoImpl partnershipsProgramsDaoImpl) {
        this.partnershipsProgramsApi = partnershipsProgramsApi;
        this.partnershipsProgramsDaoImpl = partnershipsProgramsDaoImpl;
    }

    @Cacheable(value = "programsToUser")
    public List<ProgramToUser> getProgramsFromSites() {
        log.info("PartnershipsProgramsService getPartnershipsPrograms id");
        List<ProgramToUser> programsToUser = new ArrayList<>(partnershipsProgramsDaoImpl.findAll());
        programsToUser.addAll(partnershipsProgramsApi.getProgramsFromSites(websiteId, limit));
        return programsToUser;
    }
}
