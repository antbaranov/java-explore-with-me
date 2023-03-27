package ru.practicum;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.lang.Nullable;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Slf4j
public class BaseClient {
    protected final RestTemplate rest;

    private static ResponseEntity<Object> prepareGatewayResponse(ResponseEntity<Object> response) {
        if (response.getStatusCode().is2xxSuccessful()) {
            return response;
        }
        ResponseEntity.BodyBuilder responseBuilder = ResponseEntity.status(response.getStatusCode());
        if (response.hasBody()) {
            return responseBuilder.body(response.getBody());
        }
        return responseBuilder.build();
    }

    protected <T> ResponseEntity<Object> post(String path, T body) {
        return makeAndSendRequest(HttpMethod.POST, path, null, body);
    }

    protected ResponseEntity<Object> get(String path, @Nullable Map<String, Object> parameters) {
        return makeAndSendRequest(HttpMethod.GET, path, parameters, null);
    }

    private <T> ResponseEntity<Object> makeAndSendRequest(HttpMethod method, String path, @Nullable Map<String, Object> parameters, @Nullable T body) {
        HttpEntity<T> requestEntity = new HttpEntity<>(body, defaultHeaders());

        ResponseEntity<Object> serverResponse;
        try {
            serverResponse = rest.exchange(path, method, requestEntity, Object.class);
        } catch (HttpStatusCodeException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsByteArray());
        }
        return prepareGatewayResponse(serverResponse);
    }

    private HttpHeaders defaultHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        return headers;
    }

    protected <T> Optional<T> exchange(String path,
                                       HttpMethod method,
                                       @Nullable T object,
                                       ParameterizedTypeReference<T> typeReference,
                                       Map<String, Object> parameters) {
        HttpEntity<T> requestEntity = new HttpEntity<>(object, defaultHeaders());
        ResponseEntity<T> responseEntity;
        try {
            if (parameters != null) {
                responseEntity = rest.exchange(path, method, requestEntity, typeReference, parameters);
            } else {
                responseEntity = rest.exchange(path, method, requestEntity, typeReference);
            }
        } catch (HttpStatusCodeException e) {
            log.info(Arrays.toString(e.getStackTrace()));
            return Optional.empty();
        }
        log.info(String.join(": ", "ResponseBody", String.valueOf(responseEntity.getBody())));
        return Optional.ofNullable(responseEntity.getBody());
    }

    protected <T> Optional<List<T>> exchangeAsList(String path,
                                                   HttpMethod method,
                                                   @Nullable List<T> objects,
                                                   ParameterizedTypeReference<List<T>> typeReference,
                                                   Map<String, Object> parameters) {
        HttpEntity<List<T>> requestEntity = new HttpEntity<>(objects, defaultHeaders());
        ResponseEntity<List<T>> responseEntity;
        try {
            if (parameters != null) {
                responseEntity = rest.exchange(path, method, requestEntity, typeReference, parameters);
            } else {
                responseEntity = rest.exchange(path, method, requestEntity, typeReference);
            }
        } catch (HttpStatusCodeException e) {
            log.info(Arrays.toString(e.getStackTrace()));
            return Optional.empty();
        }
        log.info(String.join(": ", "ResponseBody", String.valueOf(responseEntity.getBody())));
        return Optional.ofNullable(responseEntity.getBody());
    }
}
