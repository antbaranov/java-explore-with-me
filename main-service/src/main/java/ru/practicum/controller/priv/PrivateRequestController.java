package ru.practicum.controller.priv;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.dto.request.ParticipationRequestDto;
import ru.practicum.mapper.RequestMapper;
import ru.practicum.service.request.RequestService;

import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users/{userId}/requests")
@Slf4j
public class PrivateRequestController {

    private final RequestService requestService;

    private final RequestMapper requestMapper;

    /**
     * Добавление запроса от текущего пользователя на участие в событии
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ParticipationRequestDto createRequest(@PathVariable @Min(0) Long userId,
                                                 @RequestParam @Min(0) Long eventId) {
        log.info("Create request by userId={} for eventId={}", userId, eventId);
        return requestService.create(userId, eventId);
    }

    /**
     * Получение информации о заявках текущего пользователя на участие в чужих событиях
     */
    @GetMapping
    public List<ParticipationRequestDto> getUserRequests(@PathVariable @Min(0) Long userId) {
        log.info("GET requests by userId={}", userId);
        return requestService.getAll(userId);
    }

    /**
     * Отмена своего запроса на участие в событии
     */
    @PatchMapping("{requestId}/cancel")
    public ParticipationRequestDto cancelRequest(@PathVariable @Min(0) Long userId,
                                                 @PathVariable @Min(0) Long requestId) {
        log.info("Patch requests by userId={} for requestId={}", userId, requestId);
        return RequestMapper.toParticipationRequestDto(requestService.cancelRequest(userId, requestId));
    }
}