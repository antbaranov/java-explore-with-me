package ru.practicum.service.event;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.dto.event.EventFullDto;
import ru.practicum.entity.Category;
import ru.practicum.entity.Event;
import ru.practicum.entity.Location;
import ru.practicum.entity.SortEvent;
import ru.practicum.entity.State;
import ru.practicum.entity.User;
import ru.practicum.exception.AccessException;
import ru.practicum.exception.NotFoundException;
import ru.practicum.mapper.EventMapper;
import ru.practicum.repository.EventRepository;
import ru.practicum.repository.LocationRepository;
import ru.practicum.service.category.CategoryService;
import ru.practicum.service.user.UserService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final LocationRepository locationRepository;
    private final UserService userService;
    private final CategoryService categoryService;

    private final EventMapper eventMapper;

    @Override
    public Event create(Long userId, Event event) {
        User user = userService.getById(userId);
        Location location = getLocation(event.getLocation());
        Category category = categoryService.findById(event.getCategory().getId());
        event.setInitiator(user);
        event.setConfirmedRequests(0);
        event.setLocation(location);
        event.setCategory(category);
        event.setCreatedOn(Timestamp.valueOf(LocalDateTime.now()));
        event.setPublishedOn(Timestamp.valueOf(LocalDateTime.now()));
        event.setState(State.PENDING);
        event.setViews(0);

        return save(event);
    }

    // Запись ивента
    @Override
    public Event save(Event event) {

        return eventRepository.save(event);
    }

    @Override
    public List<Event> getAll(Long userId, int from, int size) {
        userService.getById(userId);
        return eventRepository.findAllByInitiatorId(userId, PageRequest.of(from, size));
    }

    @Override
    public List<Event> getAll(List<Long> eventIds) {

        return eventRepository.findAllById(eventIds);
    }

    @Override
    public List<EventFullDto> getAllByParameters(List<Long> users, List<State> states, List<Long> categories,
                                          Timestamp rangeStart, Timestamp rangeEnd, int from, int size) {
        if (rangeStart == null) rangeStart = Timestamp.valueOf(LocalDateTime.now().minusYears(100));
        if (rangeEnd == null) rangeEnd = Timestamp.valueOf(LocalDateTime.now().plusYears(100));

        return EventMapper.toEventFullDtoList(eventRepository.findByParameters(users, states, categories,
                rangeStart, rangeEnd, PageRequest.of(from, size)));
    }

    @Override
    public List<Event> getAllByParametersPublic(String text, List<Long> categories, Boolean paid,
                                                Timestamp rangeStart, Timestamp rangeEnd, Boolean onlyAvailable,
                                                SortEvent sort, int from, int size) {
        if (rangeStart == null) rangeStart = Timestamp.valueOf(LocalDateTime.now());
        if (rangeEnd == null) rangeEnd = Timestamp.valueOf(LocalDateTime.now().plusYears(100));
        if (text != null) text = text.toLowerCase();

        if (sort == null || sort.equals(SortEvent.EVENT_DATE)) {
            return eventRepository.findByParametersForPublicSortEventDate(
                    text,
                    categories,
                    paid,
                    rangeStart,
                    rangeEnd,
                    onlyAvailable,
                    PageRequest.of(from, size));
        } else {
            return eventRepository.findByParametersForPublicSortViews(
                    text,
                    categories,
                    paid,
                    rangeStart,
                    rangeEnd,
                    onlyAvailable,
                    PageRequest.of(from, size));
        }
    }

    @Override
    public Event getUserEventById(Long eventId, Long userId) {
        userService.getById(userId);
        return eventRepository.findByIdAndInitiatorId(eventId, userId)
                .orElseThrow(() -> new NotFoundException("Event with id=" + eventId));
    }

    @Override
    public Event getById(Long eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new NotFoundException("Event with id=" + eventId));
    }

    @Override
    public Event update(Long userId, Long eventId, Event donor) {
        Event recipient = getUserEventById(eventId, userId);
        if (recipient.getState() == State.PUBLISHED) {
            throw new AccessException("Error: event state");
        }
        recipient = updateEvent(donor, recipient);

        return save(recipient);
    }

    @Override
    public EventFullDto updateByAdmin(Long eventId, Event donor) {
        Event recipient = getById(eventId);
        if (!recipient.getState().equals(State.PENDING)) throw new AccessException("Event not pending");
        recipient = updateEvent(donor, recipient);

        return EventMapper.toEventFullDto(save(recipient));
    }

    // По тестам если ивент не найден для запроса возвращаться 409, а не 404
    @Override
    public Optional<Event> getByIdForRequest(Long eventId) {

        return eventRepository.findById(eventId);
    }

    private Event updateEvent(Event donor, Event recipient) {
        if (donor.getLocation() != null) {
            Location location = getLocation(donor.getLocation());
            donor.setLocation(location);
        }
        return EventMapper.updateEvent(donor, recipient);
    }

    private Location getLocation(Location location) {
        return locationRepository.findByLatAndLon(location.getLat(),
                location.getLon()).orElse(locationRepository.save(location));
    }
}