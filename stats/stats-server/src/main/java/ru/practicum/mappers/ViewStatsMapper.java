package ru.practicum.mappers;

import org.mapstruct.Mapper;
import ru.practicum.dto.ViewStatsDto;
import ru.practicum.enity.ViewStats;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ViewStatsMapper {
    List<ViewStatsDto> toEntityList(List<ViewStats> viewStats);
}
