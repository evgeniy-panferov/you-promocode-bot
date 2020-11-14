package ru.youpromocodebot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import ru.youpromocodebot.model.Message;
import ru.youpromocodebot.model.dto.admitad.Sites;
import ru.youpromocodebot.client.WebmasterSitesApi;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebmasterSitesService {

    private final WebmasterSitesApi webmasterSitesApi;

    public Sites getWebmasterSites() {
        log.info("WebmasterSitesService getWebmasterSites");
        return webmasterSitesApi.getWebmasterSites();
    }

    public Sites createWebmasterSites(MultiValueMap<String, String> params) {
        log.info("WebmasterSitesService createWebmasterSites params - {}", params);
        return webmasterSitesApi.createWebmasterSites(params);
    }

    public Sites updateWebmasterSites(MultiValueMap<String, String> params, String id) {
        log.info("WebmasterSitesService updateWebmasterSites params - {}, id - {}", params, id);
        return webmasterSitesApi.updateWebmasterSites(params, id);
    }

    public Message confirmationWebmasterSites(MultiValueMap<String, String> params, String id) {
        log.info("WebmasterSitesService confirmationWebmasterSites params - {}, id - {}", params, id);
        return webmasterSitesApi.confirmationWebmasterSites(params, id);
    }

    public void deleteWebmasterSites(MultiValueMap<String, String> params, String id) {
        log.info("WebmasterSitesService deleteWebmasterSites params - {}, id - {}", params, id);
        webmasterSitesApi.deleteWebmasterSites(params, id);
    }
}
