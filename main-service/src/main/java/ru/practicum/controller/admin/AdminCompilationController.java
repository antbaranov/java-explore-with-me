package ru.practicum.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.dto.compliiation.CompilationDto;
import ru.practicum.dto.compliiation.NewCompilationDto;
import ru.practicum.service.compilation.CompilationService;
import ru.practicum.validation.group.Create;
import ru.practicum.validation.group.Update;

import javax.validation.constraints.Min;

@Validated
@RestController
@RequestMapping("/admin/compilations")
@RequiredArgsConstructor
@Slf4j
public class AdminCompilationController {

    private final CompilationService compilationService;

    /**
     * Добавление новой подборки
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompilationDto createCompilation(@Validated(Create.class) @RequestBody NewCompilationDto dto) {
        log.info("Create {}", dto.toString());
        return compilationService.create(dto);
    }

    /**
     * Удаление подборки
     */
    @DeleteMapping("{compId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompilation(@PathVariable @Min(0) Long compId) {
        log.info("Delete by id={}", compId);
        compilationService.delete(compId);
    }

    /**
     * Обновление подборки
     */
    @PatchMapping("{compId}")
    public CompilationDto update(@Validated(Update.class) @RequestBody NewCompilationDto dto,
                                 @PathVariable @Min(0) Long compId) {
        log.info("Update by id={}, for {}", compId, dto.toString());
        return compilationService.update(compId, dto);
    }
}