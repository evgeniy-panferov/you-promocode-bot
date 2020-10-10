package ru.youpromocodebot.client;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.youpromocodebot.service.AdmitadAuthorization;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Data
@Slf4j
@Component
@PropertySource("classpath:properties/admitad.yml")
public class AdmitadConnectionApi {

    private RestTemplate restTemplate;

    private AdmitadAuthorization admitadAuthorization;

    public AdmitadConnectionApi(RestTemplate restTemplate, AdmitadAuthorization admitadAuthorization) {
        this.restTemplate = restTemplate;
        this.admitadAuthorization = admitadAuthorization;
    }

    public <T> T getEntity(String uri, HttpMethod httpMethod, Class<T> clazz) {
        log.info("AdmitadConnectionApi getEntity uri - {}, httpMethod - {}, clazz - {}", uri, httpMethod, clazz);

        ResponseEntity<T> response = restTemplate.exchange(uri,
                httpMethod, getHttpEntity(), clazz);
        T body = response.getBody();

        return body;
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

    public <T> T getEntity(String uri, HttpMethod httpMethod, Class<T> clazz,
                           String... uriPath) {
        log.info("AdmitadConnectionApi getEntity uri - {}, httpMethod - {}, clazz - {}, uriPath-{}",
                uri, httpMethod, clazz, uriPath);

        List<String> paths = Arrays.asList(uriPath);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(uri);

        paths.forEach(builder::path);

        ResponseEntity<T> response = restTemplate.exchange(builder.toUriString(),
                httpMethod, getHttpEntity(), clazz);

        return response.getBody();
    }

    public <T> T getEntity(String uri, HttpMethod httpMethod, Class<T> clazz,
                           MultiValueMap<String, String> param, String... uriPath) {
        log.info("AdmitadConnectionApi getEntity uri - {}, httpMethod - {}, clazz - {}, param  - {}, uriPath-{}",
                uri, httpMethod, clazz, param, uriPath);

        List<String> paths = Arrays.asList(uriPath);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(uri);

        paths.forEach(builder::path);

        param.forEach((key, value) -> builder.queryParam(key, param.get(key)));

        ResponseEntity<T> response = restTemplate.exchange(builder.toUriString(),
                httpMethod, getHttpEntity(param), clazz);

        return response.getBody();
    }

    private HttpHeaders getHttpHeaders() {
        log.info("AdmitadConnectionApi getHttpHeaders");
        String token = admitadAuthorization.getToken();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        httpHeaders.set("Authorization", "Bearer " + token);
//        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
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
