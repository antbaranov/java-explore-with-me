package ru.practicum.service.user;

import ru.practicum.dto.user.UserDto;
import ru.practicum.dto.user.UserIncomeDto;
import ru.practicum.dto.user.UserResponseDto;
import ru.practicum.entity.User;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userModelDto);

    List<UserDto> getUsers(List<Long> ids, Integer from, Integer size);

    void deleteUser(Long id);
}