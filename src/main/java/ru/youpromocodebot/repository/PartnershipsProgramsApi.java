package ru.youpromocodebot.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import ru.youpromocodebot.client.AdmitadConnection;
import ru.youpromocodebot.model.Message;
import ru.youpromocodebot.model.dto.admitad.Programs;
import ru.youpromocodebot.model.dto.user.ProgramToUser;
import ru.youpromocodebot.util.EntityToDto;

import java.util.List;

/**
 * Работа с партнерскими программами
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class PartnershipsProgramsApi {

    private final AdmitadConnection admitadConnection;

    @Value("${websiteId}")
    private String websiteId;
    @Value("${limit}")
    private String limit;

    private static final String LIST_PARTNERSHIPS_PROGRAMS_URL = "https://api.admitad.com/advcampaigns/";

    public Programs getPartnershipsPrograms() {
        log.info("PartnershipsProgramsApi getPartnershipsPrograms");
        return admitadConnection.getEntity(LIST_PARTNERSHIPS_PROGRAMS_URL, HttpMethod.GET,
                Programs.class);
    }

    public List<ProgramToUser> getProgramsFromSites() {
        log.info("PartnershipsProgramsApi getPartnershipsPrograms id - {}", websiteId);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("website", websiteId);
        map.add("limit", limit);
        return EntityToDto.convertProgramToDto(admitadConnection.getEntity(LIST_PARTNERSHIPS_PROGRAMS_URL, HttpMethod.GET,
                Programs.class, map));
    }


    public Message addSitesFromPrograms(String cId, String wId) {
        log.info("PartnershipsProgramsApi addSitesFromPrograms cId - {}, wId - {}", cId, wId);
        return admitadConnection.getEntity(LIST_PARTNERSHIPS_PROGRAMS_URL, HttpMethod.POST, Message.class,
                cId, "attach", wId);
    }

    public Message removeSitesFromPrograms(String cId, String wId) {
        log.info("PartnershipsProgramsApi removeSitesFromPrograms cId - {}, wId - {}", cId, wId);
        return admitadConnection.getEntity(LIST_PARTNERSHIPS_PROGRAMS_URL, HttpMethod.POST, Message.class,
                cId, "detach", wId);
    }

    public Message anyUrnFromPrograms(String... args) {
        log.info("PartnershipsProgramsApi removeSitesFromPrograms args - {}", args);
        return admitadConnection.getEntity(LIST_PARTNERSHIPS_PROGRAMS_URL, HttpMethod.GET, Message.class,
                args);
    }

}
