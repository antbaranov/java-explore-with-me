package ru.practicum.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EndpointHitResponseDto {
    private Long id;
    private String app;
    private String uri;
    private String ip;
    private Timestamp timestamp;
}
