package br.com.elo.rest;

import br.com.elo.fixture.Fixture;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import javax.annotation.PostConstruct;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class RestClientFixture implements Fixture {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestClientFixture.class);

    private static final String URL_SCHEME = "http";

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    private RestTemplate restTemplate;

    @Value("${server.host}")
    private String host = "localhost";

    @Value("${server.port}")
    private int port = 8080;

    private ResponseEntity<?> lastResponse = null;

    @PostConstruct
    public void init() {
        this.restTemplate = this.restTemplateBuilder
                .additionalMessageConverters(new MappingJackson2HttpMessageConverter())
                .errorHandler(new DefaultResponseErrorHandler() {
                    @Override
                    protected boolean hasError(final HttpStatus statusCode) {
                        return false;
                    }
                })
                .build();
    }


    public <T> ResponseEntity<T> request(final HttpMethod method, final Object requestBody,
                                         final Class<T> responseType, final String urlPath, final Object... uriArgs) {
        return this.request(method, requestBody, responseType, urlPath, null, uriArgs);
    }

    public <T> ResponseEntity<T> request(final HttpMethod method, final Object requestBody,
                                         final Class<T> responseType, final String urlPath, final MultiValueMap<String, String> params,
                                         final Object... uriArgs) {

        String url = this.buildUrl(urlPath, params);

        HttpHeaders headers = this.buildHeader();
        HttpEntity<?> requestEntity = new HttpEntity<>(requestBody, headers);

        LOGGER.debug("Requesting {} {} {}", method, url, Arrays.toString(uriArgs));
        LOGGER.debug("Body: {}", requestEntity.getBody());
        ResponseEntity<T> response = this.restTemplate.exchange(url, method, requestEntity,
                ParameterizedTypeReference.forType(responseType), uriArgs);

        this.setLastResponse(response);
        return response;
    }

    private void setLastResponse(final ResponseEntity<?> response) {
        this.lastResponse = response;
    }

    @SuppressWarnings("unchecked")
    public <T> ResponseEntity<T> getLastResponse() {

        return (ResponseEntity<T>) this.lastResponse;
    }

    private String buildUrl(final String urlPath, final MultiValueMap<String, String> params) {
        return UriComponentsBuilder.newInstance()
                .scheme(URL_SCHEME)
                .host(this.host)
                .port(this.port)
                .path(urlPath)
                .queryParams(params)
                .build(false)
                .toUriString();
    }

    private HttpHeaders buildHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Content-Type", "application/json");
        return headers;
    }

    @Override
    public void cleanup() {

    }
}
