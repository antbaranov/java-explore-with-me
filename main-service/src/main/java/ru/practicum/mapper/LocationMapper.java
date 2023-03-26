package ru.practicum.mapper;

import org.mapstruct.Mapper;
import ru.practicum.dto.location.LocationRequestDto;
import ru.practicum.dto.location.LocationResponseDto;
import ru.practicum.entity.Location;

@Mapper(componentModel = "spring")
public interface LocationMapper {
//    static Location toLocation(LocationRequestDto dto);

    static Location toLocation(LocationRequestDto dto) {
        if (dto == null) return null;
        return Location.builder()
                .lat(dto.getLat())
                .lon(dto.getLon())
                .build();
    }

    static LocationResponseDto toLocationResponseDto(Location location) {
        return LocationResponseDto.builder()
                .lat(location.getLat())
                .lon(location.getLon())
                .build();
    }
}