package ru.practicum.service.request;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.dto.event.EventRequestStatusUpdateRequest;
import ru.practicum.dto.event.EventRequestStatusUpdateResult;
import ru.practicum.dto.request.EventRequestStatusUpdateResultDto;
import ru.practicum.entity.Event;
import ru.practicum.entity.Request;
import ru.practicum.entity.State;
import ru.practicum.entity.Status;
import ru.practicum.entity.User;
import ru.practicum.exception.AccessException;
import ru.practicum.exception.NotFoundException;
import ru.practicum.mapper.RequestMapper;
import ru.practicum.repository.RequestRepository;
import ru.practicum.service.event.EventService;
import ru.practicum.service.user.UserService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;
    private final UserService userService;
    private final EventService eventService;

    private final RequestMapper requestMapper;

    @Override
    public List<Request> getAll(Long userId) {
        userService.getById(userId);
        return requestRepository.findAllByRequesterId(userId);
    }

    @Override
    public Request create(Long userId, Long eventId) {
        User user = userService.getById(userId);
        // По тестам если тут ivent не найден должно возвращаться 409, а не 404
        Event event = eventService.getByIdForRequest(eventId).orElseThrow(
                () -> new AccessException("Invalid request"));
        if ((Objects.equals(event.getInitiator().getId(), userId))
                || !event.getState().equals(State.PUBLISHED)
                || (event.getConfirmedRequests() >= event.getParticipantLimit())) {
            throw new AccessException("Invalid request");
        }
        Request newRequest = Request.builder()
                .created(Timestamp.valueOf(LocalDateTime.now()))
                .requester(user)
                .event(event)
                .status(Status.PENDING)
                .build();
        if (!event.getRequestModeration()) {
            newRequest.setStatus(Status.CONFIRMED);
        }

        newRequest = requestRepository.save(newRequest);

        event.setConfirmedRequests(getRequestsByEventByStatus(event.getId(), Status.CONFIRMED));
        eventService.save(event);

        return newRequest;
    }

    @Override
    public List<Request> getEventRequests(Long userId, Long eventId) {
        eventService.getUserEventById(eventId, userId);
        return requestRepository.findAllByEventId(eventId);
    }

/*    @Override
    public EventRequestStatusUpdateResult updateStatus(Long userId, Long eventId,
                                                       EventRequestStatusUpdateRequest requestStatusUpdate) {
        userService.getById(userId);
        var event = eventService.getById(eventId);
        var requests = requestRepository.findAllById(requestStatusUpdate.getRequestIds());

        var countConfirmations = 0;
        List<Request> requestsForUpdate = new ArrayList<>();

        for (Request request : requests) {
            if (!(Objects.equals(request.getEvent().getId(), eventId))) {
                throw new AccessException("Incorrect event");
            }
            *//*
             Нельзя подтвердить заявку, если уже достигнут лимит по заявкам на данное событие
             (Ожидается код ошибки 409)
             если при подтверждении данной заявки, лимит заявок для события исчерпан,
             то все неподтверждённые заявки необходимо отклонить
             *//*
            if (!event.getRequestModeration() || event.getParticipantLimit() == 0
                    || event.getConfirmedRequests() >= event.getParticipantLimit()
                    || request.getStatus() != Status.PENDING) {
                throw new AccessException("Error: confirming request");
            }
            if (requestStatusUpdate.getStatus() == Status.CONFIRMED
                    && (event.getConfirmedRequests() + countConfirmations) < event.getParticipantLimit()) {
                request.setStatus(Status.CONFIRMED);
                requestsForUpdate.add(request);
                countConfirmations++;
            } else {
                request.setStatus(Status.REJECTED);
                requestsForUpdate.add(request);
            }
        }

        // Сохраняет все запросы
        requests = requestRepository.saveAll(requestsForUpdate);

        event.setConfirmedRequests(getRequestsByEventByStatus(event.getId(), Status.CONFIRMED));
        eventService.save(event);

        EventRequestStatusUpdateResult requestResult = new EventRequestStatusUpdateResult();
        requestResult.setConfirmedRequests(requests.stream()
                .filter(r -> r.getStatus() == Status.CONFIRMED)
                .collect(Collectors.toList()));
        requestResult.setRejectedRequests(requests.stream()
                .filter(r -> r.getStatus() == Status.REJECTED)
                .collect(Collectors.toList()));
        return requestResult;
    }*/



    @Override
    public EventRequestStatusUpdateResultDto updateStatus(Long userId, Long eventId,
                                                          EventRequestStatusUpdateRequest requestStatusUpdate) {
        userService.getById(userId);
        var event = eventService.getById(eventId);
        var requests = requestRepository.findAllById(requestStatusUpdate.getRequestIds());

        var countConfirmations = 0;
        List<Request> requestsForUpdate = new ArrayList<>();

        for (Request request : requests) {
            if (!(Objects.equals(request.getEvent().getId(), eventId))) {
                throw new AccessException("Incorrect event");
            }
            /*
             Нельзя подтвердить заявку, если уже достигнут лимит по заявкам на данное событие
             (Ожидается код ошибки 409)
             если при подтверждении данной заявки, лимит заявок для события исчерпан,
             то все неподтверждённые заявки необходимо отклонить
             */
            if (!event.getRequestModeration() || event.getParticipantLimit() == 0
                    || event.getConfirmedRequests() >= event.getParticipantLimit()
                    || request.getStatus() != Status.PENDING) {
                throw new AccessException("Error: confirming request");
            }
            if (requestStatusUpdate.getStatus() == Status.CONFIRMED
                    && (event.getConfirmedRequests() + countConfirmations) < event.getParticipantLimit()) {
                request.setStatus(Status.CONFIRMED);
                requestsForUpdate.add(request);
                countConfirmations++;
            } else {
                request.setStatus(Status.REJECTED);
                requestsForUpdate.add(request);
            }
        }

        // Сохраняет все запросы
        requests = requestRepository.saveAll(requestsForUpdate);

        event.setConfirmedRequests(getRequestsByEventByStatus(event.getId(), Status.CONFIRMED));
        eventService.save(event);

        EventRequestStatusUpdateResult requestResult = new EventRequestStatusUpdateResult();
        requestResult.setConfirmedRequests(requests.stream()
                .filter(r -> r.getStatus() == Status.CONFIRMED)
                .collect(Collectors.toList()));
        requestResult.setRejectedRequests(requests.stream()
                .filter(r -> r.getStatus() == Status.REJECTED)
                .collect(Collectors.toList()));
        return requestMapper.toEventRequestStatusUpdateResultDto(requestResult);
    }


    @Override
    public Request cancelRequest(Long userId, Long requestId) {
        userService.getById(userId);
        Request request = getRequestById(requestId);
        request.setStatus(Status.CANCELED);
        return requestRepository.save(request);
    }

    private Request getRequestById(Long requestId) {
        return requestRepository.findById(requestId).orElseThrow(
                () -> new NotFoundException("Request with id=" + requestId));
    }

    private Integer getRequestsByEventByStatus(Long eventId, Status status) {
        return requestRepository.findCountRequestByEventIdAndStatus(eventId, status).orElse(0);
    }
}
