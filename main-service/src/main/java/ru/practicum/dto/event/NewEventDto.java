package ru.practicum.dto.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.practicum.dto.location.LocationRequestDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

import static ru.practicum.util.Constants.DATE_TIME;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewEventDto {

    @Size(max = 2000, min = 20)
    @NotBlank
    private String annotation;
    @NotNull
    private Long category;
    @Size(max = 7000, min = 20)
    @NotBlank
    private String description;
    @NotNull
    @JsonFormat(pattern = DATE_TIME)
    private Timestamp eventDate;
    @NotNull
    private LocationRequestDto location;
    @Builder.Default
    private boolean paid = false;
    private int participantLimit;
    private boolean requestModeration;
    @Size(max = 120, min = 3)
    @NotBlank
    private String title;
}