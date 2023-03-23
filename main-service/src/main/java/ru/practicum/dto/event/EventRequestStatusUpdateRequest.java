package ru.practicum.dto.event;

import lombok.Getter;
import lombok.Setter;
import ru.practicum.entity.Status;

import java.util.Set;

@Getter
@Setter
public class EventRequestStatusUpdateRequest {

    Set<Long> requestIds;
    Status status;
}