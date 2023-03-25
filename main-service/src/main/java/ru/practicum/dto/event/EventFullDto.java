package ru.practicum.dto.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.practicum.dto.category.CategoryResponseDto;
import ru.practicum.dto.location.LocationResponseDto;
import ru.practicum.dto.user.UserShortDto;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static ru.practicum.util.Constants.DATE_TIME;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventFullDto {

    private Long id;
    private LocationResponseDto location;
    private String annotation;
    private CategoryResponseDto category;
    private Integer confirmedRequests;
    @JsonFormat(pattern = DATE_TIME)
    private LocalDateTime createdOn;
    private String description;
    @JsonFormat(pattern = DATE_TIME)
    private Timestamp eventDate;
    private UserShortDto initiator;
    private Boolean paid;
    private Integer participantLimit;
    @JsonFormat(pattern = DATE_TIME)
    private LocalDateTime publishedOn;
    private Boolean requestModeration;
    private String state;
    private String title;
    private Integer views;
}