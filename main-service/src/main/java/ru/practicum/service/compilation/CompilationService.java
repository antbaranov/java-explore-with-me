package ru.practicum.service.compilation;

import ru.practicum.entity.Compilation;

import java.util.List;

public interface CompilationService {

    List<Compilation> getAll(boolean pinned, int from, int size);

    Compilation create(Compilation toCompilation);

    Compilation update(Long compId, Compilation compilation);

    void delete(Long compId);

    Compilation getById(Long compId);
}