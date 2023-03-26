package ru.practicum.service.event;

import ru.practicum.dto.event.EventFullDto;
import ru.practicum.dto.event.EventShortDto;
import ru.practicum.dto.event.NewEventDto;
import ru.practicum.dto.event.UpdateEventUserRequest;
import ru.practicum.entity.Event;
import ru.practicum.entity.SortEvent;
import ru.practicum.entity.State;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface EventService {

    EventFullDto create(Long userId, NewEventDto dto);

    Event save(Event event);

    EventFullDto update(Long userId, Long eventId, UpdateEventUserRequest dto);

    List<EventShortDto> getAll(Long userId, int from, int size);

//    List<Event> getAll(List<Long> eventIds);

    List<Event> getAll(List<Long> eventIds);


   /* List<Event> getAllByParameters(List<Long> users, List<State> states, List<Long> categories,
                                   Timestamp rangeStart, Timestamp rangeEnd, int from, int size);*/

    List<EventFullDto> getAllByParameters(List<Long> users, List<State> states, List<Long> categories, Timestamp rangeStart, Timestamp rangeEnd, int from, int size);

    List<Event> getAllByParametersPublic(String text, List<Long> categories, Boolean paid, Timestamp rangeStart, Timestamp rangeEnd, Boolean onlyAvailable, SortEvent sort, int from, int size);

   /* List<EventShortDto> getAllByParametersPublic(String text, List<Long> categories, Boolean paid, Timestamp rangeStart,
                                                 Timestamp rangeEnd, Boolean onlyAvailable, SortEvent sort,
                                                 int from, int size);*/

    EventFullDto getUserEventById(Long eventId, Long userId);

    Event getUserEventByIdUpdate(Long eventId, Long userId);


    Event getById(Long eventId);

    //    Event updateByAdmin(Long eventId, Event event);
    EventFullDto updateByAdmin(Long eventId, Event event);

    Optional<Event> getByIdForRequest(Long eventId);
}
