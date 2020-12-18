package ru.youpromocodebot.client;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import ru.youpromocodebot.model.AdmitadAuth;

import java.util.Collections;
import java.util.Objects;

@Data
@Component
@RequiredArgsConstructor
public class AdmitadAuthorization {

    private static final Logger log = LoggerFactory.getLogger(AdmitadAuthorization.class);

    @Value("${admitad.scope}")
    private String scope;

    private final RestTemplate restTemplate;

    private static final String AUTHORIZATION_URL = "https://api.admitad.com/token/";

    public String getToken() {
        log.info("AdmitadAuthorization getToken");

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "client_credentials");
        map.add("client_id", System.getenv("CLIENT_ID"));
        map.add("scope", scope);

        ResponseEntity<AdmitadAuth> response = restTemplate.exchange(AUTHORIZATION_URL,
                HttpMethod.POST, new HttpEntity<>(map, getHttpHeaders()), AdmitadAuth.class);

        return Objects.requireNonNull(response.getBody()).getAccessToken();
    }

    private HttpHeaders getHttpHeaders() {
        log.info("AdmitadAuthorization getHttpHeaders");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        httpHeaders.setBasicAuth(System.getenv("BASE_64_HEADER"));
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return httpHeaders;
    }

}
