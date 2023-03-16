package ru.practicum.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class EndpointHitResponseDto {
    private Long id;
    private String app;
    private String uri;
    private String ip;
    private Timestamp timestamp;
}
