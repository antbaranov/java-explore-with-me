package ru.practicum.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import ru.practicum.dto.compilation.CompilationDto;
import ru.practicum.entity.Compilation;

import java.util.List;


@Component
@Mapper(componentModel = "spring")
public interface CompilationMapper {
    CompilationDto mapToCompilationDto(Compilation compilation);

    List<CompilationDto> mapToListCompilationDto(List<Compilation> compilations);
}