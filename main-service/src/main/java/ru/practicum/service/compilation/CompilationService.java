package ru.practicum.service.compilation;

import ru.practicum.dto.compliiation.CompilationDto;
import ru.practicum.dto.compliiation.NewCompilationDto;

import java.util.List;

public interface CompilationService {

    List<CompilationDto> getAll(boolean pinned, int from, int size);

    CompilationDto create(NewCompilationDto toCompilation);


    CompilationDto update(Long compId, NewCompilationDto compilation);

    void delete(Long compId);

    CompilationDto getById(Long compId);
}