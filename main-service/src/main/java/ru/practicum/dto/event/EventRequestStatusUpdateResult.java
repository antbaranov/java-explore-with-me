package ru.practicum.dto.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.practicum.entity.Request;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventRequestStatusUpdateResult {

    private List<Request> confirmedRequests = new ArrayList<Request>();
    private List<Request> rejectedRequests = new ArrayList<Request>();
}