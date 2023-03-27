package ru.practicum.mapper;

import org.mapstruct.Mapper;
import ru.practicum.dto.EndpointHitRequestDto;
import ru.practicum.dto.EndpointHitResponseDto;
import ru.practicum.dto.ViewStatsDto;
import ru.practicum.entity.EndpointHit;
import ru.practicum.entity.ViewStats;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StatsMapper {
    EndpointHit toEndpointHit(EndpointHitRequestDto dto);

    EndpointHitResponseDto toEndpointHitResponseDto(EndpointHit entity);

    List<ViewStatsDto> toListViewStatsResponseDto(List<ViewStats> viewStatsList);

    ViewStatsDto toViewStatsResponseDto(ViewStats viewStats);
}