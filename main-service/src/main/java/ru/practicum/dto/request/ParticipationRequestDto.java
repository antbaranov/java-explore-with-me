package ru.practicum.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.practicum.enums.RequestStatus;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static ru.practicum.util.Constants.DATE;

@Getter
@Setter
@Builder
public class ParticipationRequestDto {
    private Long id;
    @NotNull
    private Long event;
    @NotNull
    private Long requester;
    private RequestStatus status;
    @JsonFormat(pattern = DATE)
    private LocalDateTime created;
}