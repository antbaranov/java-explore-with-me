package ru.practicum.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import ru.practicum.dto.EndpointHitDto;
import ru.practicum.dto.ViewStatsDto;
import ru.practicum.services.StatService;

import java.time.LocalDateTime;
import java.util.List;

import static ru.practicum.util.Constants.DATE;

@RestController
@RequiredArgsConstructor
public class StatController {
    private final StatService statService;

    @PostMapping("/hit")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveHit(@RequestBody EndpointHitDto endpointHitDto) {
        statService.saveHit(endpointHitDto);
    }

    @GetMapping("/stats")
    public List<ViewStatsDto> getStats(@DateTimeFormat(pattern = DATE)
                                       @RequestParam(value = "start") LocalDateTime start,
                                       @DateTimeFormat(pattern = DATE)
                                       @RequestParam(value = "end") LocalDateTime end,
                                       @RequestParam List<String> uris,
                                       @RequestParam(required = false, defaultValue = "false") Boolean unique) {
        return statService.getStats(start, end, uris, unique);
    }
}
