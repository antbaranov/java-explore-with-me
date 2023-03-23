package ru.practicum.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.dto.event.EventFullDto;
import ru.practicum.dto.event.UpdateEventAdminRequest;
import ru.practicum.entity.State;
import ru.practicum.mapper.EventMapper;
import ru.practicum.service.event.EventService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.sql.Timestamp;
import java.util.List;

@Validated
@RestController
@RequestMapping("/admin/events")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Slf4j
public class AdminEventController {

    private final EventService eventService;
    private final EventMapper eventMapper;

    /**
     * Редактирование данных события и его статуса (отклонение/публикация)
     */
    @PatchMapping("{eventId}")
    public EventFullDto update(@PathVariable @Min(0) Long eventId,
                               @Valid @RequestBody UpdateEventAdminRequest request) {
        return eventMapper.toEventFullDto(eventService.updateByAdmin(eventId,
                eventMapper.toEvent(request)));
    }

    /**
     * Возвращает полную информацию обо всех событиях подходящих под переданные условия
     */
    @GetMapping
    public List<EventFullDto> getAllByParameters(
            @RequestParam(required = false) List<Long> users,
            @RequestParam(required = false) List<State> states,
            @RequestParam(required = false) List<Long> categories,
            @RequestParam(required = false) Timestamp rangeStart,
            @RequestParam(required = false) Timestamp rangeEnd,
            @RequestParam(defaultValue = "0", required = false) @Min(0) final int from,
            @RequestParam(defaultValue = "10", required = false) @Min(1) final int size) {
        log.info("GET events by users={}, states={}, categories={}, rangeStart={}, rangeEnd{}, from={}, size={}",
                users, states, categories, rangeStart, rangeEnd, from, size);
        return eventMapper.toEventFullDtoList(eventService.getAllByParameters(users, states, categories,
                rangeStart, rangeEnd, from, size));
    }
}