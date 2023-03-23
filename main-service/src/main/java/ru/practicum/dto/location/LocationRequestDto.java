package ru.practicum.dto.location;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LocationRequestDto {
    private float lat;
    private float lon;
}