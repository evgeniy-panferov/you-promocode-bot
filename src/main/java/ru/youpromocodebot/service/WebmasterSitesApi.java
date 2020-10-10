package ru.youpromocodebot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import ru.youpromocodebot.client.AdmitadConnectionApi;
import ru.youpromocodebot.model.Message;
import ru.youpromocodebot.model.dto.admitad.Sites;

@Slf4j
@Component
public class WebmasterSitesApi {

    private final AdmitadConnectionApi admitadConnectionApi;

    private static final String LIST_WEBMASTER_SITES = "https://api.admitad.com/websites/v2/";

    private static final String CREATE_WEBMASTER_SITE = "https://api.admitad.com/websites/v2/";

    private static final String UPDATE_WEBMASTER_SITE = "https://api.admitad.com/websites/v2/update/{id}/";

    private static final String CONFIRMATION_WEBMASTER_SITE = "https://api.admitad.com/websites/v2/verify/{id}/";

    private static final String DELETE_WEBMASTER_SITE = "https://api.admitad.com/websites/v2/delete/{id}/";

    public WebmasterSitesApi(AdmitadConnectionApi admitadConnectionApi) {
        this.admitadConnectionApi = admitadConnectionApi;
    }

    public Sites getWebmasterSites() {
        log.info("WebmasterSitesApi getWebmasterSites");
        return admitadConnectionApi.getEntity(LIST_WEBMASTER_SITES, HttpMethod.GET, Sites.class);
    }

    public Sites createWebmasterSites(MultiValueMap<String, String> params) {
        log.info("WebmasterSitesApi createWebmasterSites");
        String str = CREATE_WEBMASTER_SITE;
        return null;
    }

    public Sites updateWebmasterSites(MultiValueMap<String, String> params, String id) {
        log.info("WebmasterSitesApi updateWebmasterSites");
        String str = UPDATE_WEBMASTER_SITE;
        return null;
    }

    public Message confirmationWebmasterSites(MultiValueMap<String, String> params, String id) {
        log.info("WebmasterSitesApi confirmationWebmasterSites");
        String str = CONFIRMATION_WEBMASTER_SITE;
        return null;
    }

    public void deleteWebmasterSites(MultiValueMap<String, String> params, String id) {
        log.info("WebmasterSitesApi deleteWebmasterSites");
        String str = DELETE_WEBMASTER_SITE;
    }


}
