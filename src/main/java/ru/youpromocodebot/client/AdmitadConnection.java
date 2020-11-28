package ru.youpromocodebot.client;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;

@Data
@Component
@RequiredArgsConstructor
public class AdmitadConnection {

    private static final Logger log = LoggerFactory.getLogger(AdmitadConnection.class);

    private final RestTemplate restTemplate;

    private final AdmitadAuthorization admitadAuthorization;

    public <T> T getEntity(String uri, HttpMethod httpMethod, Class<T> clazz) {
        log.info("AdmitadConnectionApi getEntity uri - {}, httpMethod - {}, clazz - {}", uri, httpMethod, clazz);

        ResponseEntity<T> response = restTemplate.exchange(uri,
                httpMethod, getHttpEntity(), clazz);

        return response.getBody();
    }

    public <T> T getEntity(String uri, HttpMethod httpMethod, Class<T> clazz,
                           MultiValueMap<String, String> param) {
        log.info("AdmitadConnectionApi getEntity uri - {}, httpMethod - {}, clazz - {}, param - {}",
                uri, httpMethod, clazz, param);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(uri)
                .queryParams(param);

        ResponseEntity<T> response = restTemplate.exchange(uriBuilder.toUriString(),
                httpMethod, getHttpEntity(param), clazz);

        return response.getBody();
    }

    private HttpHeaders getHttpHeaders() {
        log.info("AdmitadConnectionApi getHttpHeaders");
        String token = admitadAuthorization.getToken();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        httpHeaders.set("Authorization", "Bearer " + token);
        return httpHeaders;
    }

    private HttpEntity<String> getHttpEntity() {
        log.info("AdmitadConnectionApi getHttpEntity");
        return new HttpEntity<>(getHttpHeaders());
    }

    private HttpEntity<MultiValueMap<String, String>> getHttpEntity(MultiValueMap<String, String> params) {
        log.info("AdmitadConnectionApi getHttpEntity params - {}", params);
        return new HttpEntity<>(params, getHttpHeaders());
    }

}
