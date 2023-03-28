package ru.practicum.service;

import ru.practicum.dto.EndpointHitDto;
import ru.practicum.dto.ViewStatsDto;

import java.sql.Timestamp;
import java.util.List;

public interface StatsService {

//    EndpointHitDto createHit(EndpointHitRequestDto endpointHitRequestDto);

    EndpointHitDto createHit(EndpointHitDto endpointHitDto);

    List<ViewStatsDto> getStats(Timestamp start, Timestamp end, List<String> uris, boolean unique);
}