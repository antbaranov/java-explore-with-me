package ru.practicum.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.practicum.dto.EndpointHitDto;
import ru.practicum.enity.EndpointHit;

import static ru.practicum.util.Constants.DATE;


@Mapper(componentModel = "spring")
public interface EndpointHitMapper {
    @Mapping(target = "timestamp", source = "timestamp", dateFormat = DATE)
    EndpointHit toEntity(EndpointHitDto endpointHitDto);
}
