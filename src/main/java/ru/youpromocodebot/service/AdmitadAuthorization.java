package ru.youpromocodebot.service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import ru.youpromocodebot.model.AdmitadAuth;

import java.util.Collections;

@Data
@Slf4j
@Component
@PropertySource("classpath:properties/admitad.yml")
public class AdmitadAuthorization {

    @Value("${base64_header}")
    private String base64Header;

    @Value("${client_id}")
    private String clientId;

    @Value("${scope}")
    private String scope;

    private RestTemplate restTemplate;

    public AdmitadAuthorization(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private static final String AUTHORIZATION_URL = "https://api.admitad.com/token/";

    public String getToken() {
        log.info("AdmitadAuthorization getToken");

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "client_credentials");
        map.add("client_id", clientId);
        map.add("scope", scope);

        ResponseEntity<AdmitadAuth> response = restTemplate.exchange(AUTHORIZATION_URL,
                HttpMethod.POST, new HttpEntity<>(map, getHttpHeaders()), AdmitadAuth.class);

        return response.getBody().getAccessToken();
    }

    private HttpHeaders getHttpHeaders() {
        log.info("AdmitadConnectionApi getHttpHeaders");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        httpHeaders.setBasicAuth(base64Header);
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return httpHeaders;
    }

}
