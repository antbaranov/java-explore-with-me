package ru.practicum.service.request;

import ru.practicum.dto.event.EventRequestStatusUpdateRequest;
import ru.practicum.dto.request.EventRequestStatusUpdateResultDto;
import ru.practicum.dto.request.ParticipationRequestDto;

import java.util.List;

public interface RequestService {

    List<ParticipationRequestDto> getAll(Long userId);

    ParticipationRequestDto create(Long userId, Long eventId);

    List<ParticipationRequestDto> getEventRequests(Long userId, Long eventId);

    EventRequestStatusUpdateResultDto updateStatus(Long userId, Long eventId,
                                                   EventRequestStatusUpdateRequest requestStatusUpdate);

    ParticipationRequestDto cancelRequest(Long userId, Long requestId);
}
