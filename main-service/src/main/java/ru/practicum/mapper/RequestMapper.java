package ru.practicum.mapper;

import org.mapstruct.Mapper;
import ru.practicum.dto.event.EventRequestStatusUpdateResult;
import ru.practicum.dto.request.EventRequestStatusUpdateResultDto;
import ru.practicum.dto.request.ParticipationRequestDto;
import ru.practicum.entity.Request;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RequestMapper {

    static ParticipationRequestDto toParticipationRequestDto(Request request) {

        return ParticipationRequestDto.builder()
                .id(request.getId())
                .event(request.getEvent().getId())
                .requester(request.getRequester().getId())
                .status(request.getStatus())
                .created(request.getCreated().toLocalDateTime())
                .build();
    }

    List<ParticipationRequestDto> toParticipationRequestDtoList(List<Request> requests);

    EventRequestStatusUpdateResultDto toEventRequestStatusUpdateResultDto(EventRequestStatusUpdateResult result);
}