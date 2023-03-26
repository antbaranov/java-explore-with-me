package ru.practicum.service.request;

import ru.practicum.dto.event.EventRequestStatusUpdateRequest;
import ru.practicum.dto.request.EventRequestStatusUpdateResultDto;
import ru.practicum.dto.request.ParticipationRequestDto;
import ru.practicum.entity.Request;

import java.util.List;

public interface RequestService {

    List<Request> getAll(Long userId);

    Request create(Long userId, Long eventId);

    List<ParticipationRequestDto> getEventRequests(Long userId, Long eventId);

    EventRequestStatusUpdateResultDto updateStatus(Long userId, Long eventId,
                                                   EventRequestStatusUpdateRequest requestStatusUpdate);

    Request cancelRequest(Long userId, Long requestId);
}
