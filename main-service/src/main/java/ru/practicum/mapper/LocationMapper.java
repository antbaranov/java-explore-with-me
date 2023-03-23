package ru.practicum.mapper;

import org.mapstruct.Mapper;
import ru.practicum.dto.location.LocationRequestDto;
import ru.practicum.dto.location.LocationResponseDto;
import ru.practicum.entity.Location;

@Mapper(componentModel = "spring")
public interface LocationMapper {
    Location toLocation(LocationRequestDto dto);

    static LocationResponseDto toLocationResponseDto(Location location) {
        return LocationResponseDto.builder()
                .lat(location.getLat())
                .lon(location.getLon())
                .build();
    }
}