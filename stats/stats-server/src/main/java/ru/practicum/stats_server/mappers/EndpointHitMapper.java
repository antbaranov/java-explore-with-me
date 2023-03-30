package ru.practicum.stats_server.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.practicum.dto.EndpointHitDto;
import ru.practicum.stats_server.enity.EndpointHit;

import static ru.practicum.main_service.util.Constants.DATE;


@Mapper(componentModel = "spring")
public interface EndpointHitMapper {
    @Mapping(target = "timestamp", source = "timestamp", dateFormat = DATE)
    EndpointHit toEntity(EndpointHitDto endpointHitDto);
}
