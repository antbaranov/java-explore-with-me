package ru.practicum;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.reflect.TypeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.practicum.dto.EndpointHitRequestDto;
import ru.practicum.dto.EndpointHitResponseDto;
import ru.practicum.dto.ViewStatsDto;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
public class StatsClient extends BaseClient {

    private static final String STATS_ENDPOINT = "/stats";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static final String APPLICATION_NAME = "ewm-main-service";

    @Autowired
    public StatsClient(@Value("${stats-server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(builder.uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl))
                .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                .build()
        );
    }

    public void createHit(HttpServletRequest request) {
        final EndpointHitRequestDto hit = EndpointHitRequestDto.builder()
                .app(APPLICATION_NAME)
                .uri(request.getRequestURI())
                .ip(request.getRemoteAddr())
                .timestamp(Timestamp.from(Instant.now()))
                .build();
        post("/hit", hit);
    }

    public Optional<EndpointHitResponseDto> postEndpointHit(EndpointHitResponseDto endpointHitDto) {
        return exchange(
                "/hit",
                HttpMethod.POST,
                endpointHitDto,
                ParameterizedTypeReference.forType(TypeUtils.parameterize(EndpointHitResponseDto.class)),
                null);
    }
    public Optional<List<ViewStatsDto>> getViewStats(
            LocalDateTime start,
            LocalDateTime end,
            List<String> uris,
            Boolean unique
    ) {
        Map<String, Object> parameters = Map.of(
                "start", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(start),
                "end", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(end),
                "uris", uris.stream().collect(Collectors.joining(",")),
                "unique", unique);
        return exchangeAsList(
                "/stats?start={start}&end={end}&uris={uris}&unique={unique}",
                HttpMethod.GET,
                null,
                ParameterizedTypeReference.forType(TypeUtils.parameterize(List.class, ViewStatsDto.class)),
                parameters
        );
    }

    public ResponseEntity<Object> getStats(LocalDateTime start,
                                           LocalDateTime end,
                                           List<String> uris,
                                           boolean unique) {

        String paramsUri = uris.stream().reduce("", (result, uri) -> uri + "," + result);

        Map<String, Object> parameters = Map.of(
                "start", start.format(FORMATTER),
                "end", end.format(FORMATTER),
                "uris", paramsUri,
                "unique", unique);

        return get(STATS_ENDPOINT + "?start={start}&end={end}&uris={uris}&unique={unique}", parameters);
    }


    public Optional<List<ViewStatsDto>> getViewStats(
            LocalDateTime start,
            LocalDateTime end,
            List<String> uris,
            Boolean unique
    ) {
        Map<String, Object> parameters = Map.of(
                "start", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(start),
                "end", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(end),
                "uris", uris.stream().collect(Collectors.joining(",")),
                "unique", unique);
        return exchangeAsList(
                "/stats?start={start}&end={end}&uris={uris}&unique={unique}",
                HttpMethod.GET,
                null,
                ParameterizedTypeReference.forType(TypeUtils.parameterize(List.class, ViewStatsDto.class)),
                parameters
        );
    }
}