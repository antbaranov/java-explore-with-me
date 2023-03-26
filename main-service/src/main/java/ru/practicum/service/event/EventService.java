package ru.practicum.service.event;

import ru.practicum.dto.event.EventFullDto;
import ru.practicum.entity.Event;
import ru.practicum.entity.SortEvent;
import ru.practicum.entity.State;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface EventService {

    Event create(Long userId, Event event);

    Event save(Event event);

    Event update(Long userId, Long eventId, Event donor);

    List<Event> getAll(Long userId, int from, int size);

    List<Event> getAll(List<Long> eventIds);

   /* List<Event> getAllByParameters(List<Long> users, List<State> states, List<Long> categories,
                                   Timestamp rangeStart, Timestamp rangeEnd, int from, int size);*/

    List<EventFullDto> getAllByParameters(List<Long> users, List<State> states, List<Long> categories,
                                          Timestamp rangeStart, Timestamp rangeEnd, int from, int size);

    List<Event> getAllByParametersPublic(String text, List<Long> categories, Boolean paid, Timestamp rangeStart,
                                         Timestamp rangeEnd, Boolean onlyAvailable, SortEvent sort,
                                         int from, int size);

   /* List<EventShortDto> getAllByParametersPublic(String text, List<Long> categories, Boolean paid, Timestamp rangeStart,
                                                 Timestamp rangeEnd, Boolean onlyAvailable, SortEvent sort,
                                                 int from, int size);*/

    Event getUserEventById(Long eventId, Long userId);

    Event getById(Long eventId);

    //    Event updateByAdmin(Long eventId, Event event);
    EventFullDto updateByAdmin(Long eventId, Event event);

    Optional<Event> getByIdForRequest(Long eventId);
}
