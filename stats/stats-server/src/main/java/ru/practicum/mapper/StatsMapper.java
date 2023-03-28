package ru.practicum.mapper;

import org.mapstruct.Mapper;
import ru.practicum.dto.EndpointHitDto;
import ru.practicum.dto.ViewStatsDto;
import ru.practicum.entity.EndpointHit;
import ru.practicum.entity.ViewStats;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StatsMapper {
    EndpointHit toEndpointHit(EndpointHitDto endpointHitDto);

    EndpointHitDto toEndpointHitDto(EndpointHit endpointHit);

    List<ViewStatsDto> toViewStatsDtoList(List<ViewStats> viewStatsList);

    ViewStatsDto toViewStatsDto(ViewStats viewStats);
}