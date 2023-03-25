package ru.practicum.dto.event;

import lombok.Getter;
import lombok.Setter;
import ru.practicum.entity.Status;

import java.util.Set;

@Getter
@Setter
public class EventRequestStatusUpdateRequest {

    private Set<Long> requestIds;
    private Status status;
}