package ru.youpromocodebot.client;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import ru.youpromocodebot.model.dto.admitad.Programs;
import ru.youpromocodebot.model.dto.user.ProgramToUser;

import java.text.MessageFormat;
import java.util.List;

import static ru.youpromocodebot.util.EntityToDto.convertProgramToDto;

/**
 * Работа с партнерскими программами
 */

@Component
@RequiredArgsConstructor
public class PartnershipsProgramsApi {

    private static final Logger log = LoggerFactory.getLogger(PartnershipsProgramsApi.class);

    @Value("${websiteId}")
    private String websiteId;
    @Value("${limit}")
    private String limit;

    private final AdmitadConnection admitadConnection;

    private static final String LIST_PARTNERSHIPS_PROGRAMS_URL_FOR_WEBSITE_ID = "https://api.admitad.com/advcampaigns/website/{0}/";

    public List<ProgramToUser> getProgramsFromSites() {
        log.info("PartnershipsProgramsApi getProgramsFromSites id - {}", websiteId);
        String url = MessageFormat.format(LIST_PARTNERSHIPS_PROGRAMS_URL_FOR_WEBSITE_ID, websiteId);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("limit", limit);
        return convertProgramToDto(admitadConnection.getEntity(url, HttpMethod.GET,
                Programs.class, map));
    }
}
