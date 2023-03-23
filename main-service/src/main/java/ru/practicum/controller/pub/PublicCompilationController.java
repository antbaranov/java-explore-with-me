package ru.practicum.controller.pub;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.dto.compliiation.CompilationDto;
import ru.practicum.mapper.CompilationMapper;
import ru.practicum.service.compilation.CompilationService;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Validated
@RestController
@RequestMapping("/compilations")
@RequiredArgsConstructor
@Slf4j
public class PublicCompilationController {

    private final CompilationService compilationService;
    private final CompilationMapper compilationMapper;

    /**
     * Получение подборок событий
     */
    @GetMapping
    public List<CompilationDto> getAll(
            @RequestParam(required = false) boolean pinned,
            @PositiveOrZero @RequestParam(defaultValue = "0", required = false) @Min(0) int from,
            @Positive @RequestParam(defaultValue = "10", required = false) @Min(1) int size) {
        log.info("GET compilations pinned={}, from={}, size={}", pinned, from, size);
        return compilationMapper.toCompilationDtoList(
                compilationService.getAll(pinned, from, size));
    }

    /**
     * Получение подборки события по его id
     */
    @GetMapping("{compId}")
    public CompilationDto getById(@PathVariable @Min(0) Long compId) {
        log.info("GET compilation compId={}", compId);
        return compilationMapper.toCompilationDto(
                compilationService.getById(compId));
    }
}