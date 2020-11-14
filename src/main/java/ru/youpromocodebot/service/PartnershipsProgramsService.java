package ru.youpromocodebot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.youpromocodebot.model.Message;
import ru.youpromocodebot.model.dto.admitad.Programs;
import ru.youpromocodebot.model.dto.user.ProgramToUser;
import ru.youpromocodebot.client.PartnershipsProgramsApi;

import java.util.List;

/**
 * Работа с партнерскими программами
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class PartnershipsProgramsService {

    private final PartnershipsProgramsApi partnershipsProgramsApi;

    @Value("${websiteId}")
    private String websiteId;
    @Value("${limit}")
    private String limit;

    public Programs getPartnershipsPrograms() {
        log.info("PartnershipsProgramsService getPartnershipsPrograms");
        return partnershipsProgramsApi.getPartnershipsPrograms();
    }

    public List<ProgramToUser> getProgramsFromSites() {
        log.info("PartnershipsProgramsService getPartnershipsPrograms id");
        return partnershipsProgramsApi.getProgramsFromSites(websiteId,limit);
    }

    public Message addSitesFromPrograms(String cId, String wId) {
        log.info("PartnershipsProgramsService addSitesFromPrograms cId - {}, wId - {}", cId, wId);
        return partnershipsProgramsApi.addSitesFromPrograms(cId, wId);
    }

    public Message removeSitesFromPrograms(String cId, String wId) {
        log.info("PartnershipsProgramsService removeSitesFromPrograms cId - {}, wId - {}", cId, wId);
        return partnershipsProgramsApi.removeSitesFromPrograms(cId, wId);
    }

    public Message anyUrnFromPrograms(String... args) {
        log.info("PartnershipsProgramsService removeSitesFromPrograms args - {}", args);
        return partnershipsProgramsApi.anyUrnFromPrograms(args);
    }
}
