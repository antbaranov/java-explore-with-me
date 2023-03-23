package ru.practicum.dto.compliiation;

import lombok.Getter;
import lombok.Setter;
import ru.practicum.validation.group.Create;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
public class NewCompilationDto {
    @NotNull(groups = {Create.class})
    private Set<Long> events;
    @NotNull(groups = {Create.class})
    private Boolean pinned;
    @NotNull(groups = {Create.class})
    private String title;
}