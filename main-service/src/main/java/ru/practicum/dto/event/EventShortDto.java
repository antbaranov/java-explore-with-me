package ru.practicum.dto.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.practicum.dto.category.CategoryResponseDto;
import ru.practicum.dto.user.UserShortDto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

import static ru.practicum.util.Constants.DATE_TIME;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NotNull
public class EventShortDto {

    public Long id;
    @NotNull
    @Size(min = 20, max = 2000)
    private String annotation;
    @NotNull
    private CategoryResponseDto category;
    private Integer confirmedRequests;
    @NotNull
    @JsonFormat(pattern = DATE_TIME)
    private Timestamp eventDate;
    private UserShortDto initiator;
    private Boolean paid;
    @NotNull
    @Size(min = 3, max = 120)
    private String title;
    private Integer views;
}