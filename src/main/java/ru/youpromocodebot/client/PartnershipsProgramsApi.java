package ru.youpromocodebot.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import ru.youpromocodebot.model.Message;
import ru.youpromocodebot.model.dto.admitad.Programs;
import ru.youpromocodebot.model.dto.user.ProgramToUser;
import ru.youpromocodebot.util.EntityToDto;

import java.text.MessageFormat;
import java.util.List;

/**
 * Работа с партнерскими программами
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class PartnershipsProgramsApi {

    private final AdmitadConnection admitadConnection;

    private static final String LIST_PARTNERSHIPS_PROGRAMS_URL = "https://api.admitad.com/advcampaigns/";
    private static final String LIST_PARTNERSHIPS_PROGRAMS_URL_FOR_WEBSITE_ID = "https://api.admitad.com/advcampaigns/website/{0}/";

    public Programs getPartnershipsPrograms() {
        log.info("PartnershipsProgramsApi getPartnershipsPrograms");
        return admitadConnection.getEntity(LIST_PARTNERSHIPS_PROGRAMS_URL, HttpMethod.GET,
                Programs.class);
    }

    @Cacheable(value = "programsToUser", key = "#websiteId")
    public List<ProgramToUser> getProgramsFromSites(String websiteId, String limit) {
        log.info("PartnershipsProgramsApi getProgramsFromSites id - {}", websiteId);
        String url = MessageFormat.format(LIST_PARTNERSHIPS_PROGRAMS_URL_FOR_WEBSITE_ID, websiteId);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("limit", limit);
        return EntityToDto.convertProgramToDto(admitadConnection.getEntity(url, HttpMethod.GET,
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
