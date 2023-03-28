package ru.practicum.service.stats;

import ru.practicum.dto.ViewStatsDto;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface StatisticsService {

    void makeView(HttpServletRequest request);

    List<ViewStatsDto> getSomeViews(LocalDateTime rangeStart, LocalDateTime rangeEnd,
                                    List<Long> idList,
                                    String uri,
                                    Boolean unique);

    Optional<ViewStatsDto> getView(LocalDateTime rangeStart, LocalDateTime rangeEnd,
                                   String uri,
                                   Boolean unique);
}
