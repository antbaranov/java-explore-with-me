package ru.practicum.service.stats;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.StatClient2;
import ru.practicum.dto.EndpointHitDto;
import ru.practicum.dto.ViewStatsDto;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {
    private static final String APP_NAME = "ewm-main-service";
    private final StatClient2 statsClient;

    @Override
    public void makeView(HttpServletRequest request) {
        statsClient.postEndpointHit(EndpointHitDto.fromHttpServletRequest(request, APP_NAME));
    }

    @Override
    public List<ViewStatsDto> getSomeViews(LocalDateTime rangeStart,
                                           LocalDateTime rangeEnd,
                                           List<Long> idList,
                                           String uri,
                                           Boolean unique) {
        List<String> uris = getUriList(idList, uri);
        Optional<List<ViewStatsDto>> viewsListOptional = statsClient.getViewStats(rangeStart, rangeEnd, uris, unique);
        return viewsListOptional.orElse(Collections.emptyList());
    }

    @Override
    public Optional<ViewStatsDto> getView(LocalDateTime rangeStart,
                                          LocalDateTime rangeEnd,
                                          String uri,
                                          Boolean unique) {
        Optional<List<ViewStatsDto>> viewsListOptional =
                statsClient.getViewStats(rangeStart, rangeEnd, List.of(uri), unique);
        if (viewsListOptional.isEmpty()) {
            return Optional.empty();
        }
        if (viewsListOptional.get().isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(viewsListOptional.get().get(0));
    }

    private List<String> getUriList(List<Long> eventIdList, final String uri) {
        List<String> uris = new ArrayList<>();
        StringBuilder sb;
        for (Long id : eventIdList) {
            sb = new StringBuilder();
            sb.append(uri).append("/").append(id.toString());
            uris.add(sb.toString());
        }
        return uris;
    }
}
