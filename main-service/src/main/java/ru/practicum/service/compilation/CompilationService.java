package ru.practicum.service.compilation;

import ru.practicum.dto.compliiation.CompilationDto;
import ru.practicum.dto.compliiation.NewCompilationDto;
import ru.practicum.entity.Compilation;

import java.util.List;

public interface CompilationService {

    List<Compilation> getAll(boolean pinned, int from, int size);

    CompilationDto create(NewCompilationDto toCompilation);


    CompilationDto update(Long compId, NewCompilationDto compilation);

    void delete(Long compId);

    Compilation getById(Long compId);
}