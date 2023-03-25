package ru.practicum.service.user;

import org.springframework.transaction.annotation.Transactional;
import ru.practicum.dto.user.UserDto;
import ru.practicum.dto.user.UserIncomeDto;
import ru.practicum.dto.user.UserResponseDto;
import ru.practicum.entity.User;

import java.util.List;

public interface UserService {

    UserDto create(UserIncomeDto userIncomeDto);

    List<User> getUsers(List<Long> ids, int from, int size);

    User getById(Long userId);

    void deleteById(Long userId);
}
