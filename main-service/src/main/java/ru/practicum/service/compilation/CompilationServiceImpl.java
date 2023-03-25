package ru.practicum.service.compilation;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.dto.compliiation.CompilationDto;
import ru.practicum.dto.compliiation.NewCompilationDto;
import ru.practicum.entity.Compilation;
import ru.practicum.entity.Event;
import ru.practicum.exception.NotFoundException;
import ru.practicum.mapper.CompilationMapper;
import ru.practicum.repository.CompilationRepository;
import ru.practicum.service.event.EventService;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CompilationServiceImpl implements CompilationService {

    private final CompilationRepository compilationRepository;
    private final EventService eventService;

    private final CompilationMapper compilationMapper;

    @Override
    public List<Compilation> getAll(boolean pinned, int from, int size) {
        return compilationRepository.findAllByPinned(pinned, PageRequest.of(from, size));
    }

    @Override
    public CompilationDto create(NewCompilationDto dto) {
        Compilation compilation = CompilationMapper.toCompilation(dto);
        compilation.setEvents(new HashSet<>(eventService.getAll(
                compilation.getEvents().stream()
                        .map(Event::getId)
                        .collect(Collectors.toList())
        )));

        return compilationMapper.toCompilationDto(compilationRepository.save(compilation));
    }

    @Override
    public CompilationDto update(Long compId, NewCompilationDto dto) {
        Compilation compilation = CompilationMapper.toCompilation(dto);
        Compilation recipient = getById(compId);
        return compilationMapper.toCompilationDto(
                compilationRepository.save(CompilationMapper.update(recipient, compilation)));
    }

    @Override
    public void delete(Long compId) {
        getById(compId);
        compilationRepository.deleteById(compId);
    }

    @Override
    public Compilation getById(Long compId) {
        return compilationRepository.findById(compId).orElseThrow(
                () -> new NotFoundException("List with id=" + compId));
    }
}