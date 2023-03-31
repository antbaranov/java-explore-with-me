package ru.practicum.main_service.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NonNull
public class NewUserDto {
    @NotBlank
    private String name;
    @NotBlank
    @Email
    private String email;
}