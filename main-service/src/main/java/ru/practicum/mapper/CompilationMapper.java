package ru.practicum.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.practicum.dto.compliiation.CompilationDto;
import ru.practicum.dto.compliiation.NewCompilationDto;
import ru.practicum.entity.Compilation;
import ru.practicum.entity.Event;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CompilationMapper {

  /*  static Compilation toCompilation(NewCompilationDto dto) {
        return Compilation.builder()
                .pinned(dto.getPinned())
                .title(dto.getTitle())
                .events(dto.getEvents().stream().map(CompilationMapper::makeEvent).collect(Collectors.toSet()))
                .build();
    }*/

    @Mapping(target = "compilation", expression = "java(setCompilation(NewCompilationDto dto))")
    Compilation toCompilation(NewCompilationDto dto);

    default Compilation setCompilation(NewCompilationDto dto) {
        return Compilation.builder()
                .pinned(dto.getPinned())
                .title(dto.getTitle())
                .events(dto.getEvents().stream().map(CompilationMapper::makeEvent)
                        .collect(Collectors.toSet()))
                .build();
    }

    List<CompilationDto> toCompilationDtoList(List<Compilation> compilations);

    CompilationDto toCompilationDto(Compilation compilation);

    Compilation toCompilationUpd(CompilationDto compilationDto);

    /*static Compilation update(Compilation recipient, Compilation donor) {
        if (donor.getEvents() != null) recipient.setEvents(donor.getEvents());
        if (donor.getPinned() != null) recipient.setPinned(donor.getPinned());
        if (donor.getTitle() != null) recipient.setTitle(donor.getTitle());
        return recipient;
    }*/

    void update(Compilation recipient, @MappingTarget Compilation donor);

    private static Event makeEvent(Long id) {
        return Event.builder()
                .id(id)
                .build();
    }
}