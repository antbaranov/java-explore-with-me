package ru.practicum.service;

import ru.practicum.dto.EndpointHitDto;
import ru.practicum.dto.ViewStatsDto;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public interface StatsService {

//    EndpointHitDto createHit(EndpointHitRequestDto endpointHitRequestDto);

    EndpointHitDto createHit(EndpointHitDto endpointHitDto);

    List<ViewStatsDto> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, boolean unique);
}