package ru.practicum.service;

import ru.practicum.dto.EndpointHitRequestDto;
import ru.practicum.dto.EndpointHitResponseDto;
import ru.practicum.dto.ViewStatsResponseDto;

import java.sql.Timestamp;
import java.util.List;

public interface StatsService {

    EndpointHitResponseDto createHit(EndpointHitRequestDto endpointHitRequestDto);

    List<ViewStatsResponseDto> getStats(Timestamp start, Timestamp end, List<String> uris, boolean unique);
}