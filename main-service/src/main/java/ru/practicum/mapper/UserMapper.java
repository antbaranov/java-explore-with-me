package ru.practicum.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.practicum.dto.user.UserDto;
import ru.practicum.dto.user.UserIncomeDto;
import ru.practicum.dto.user.UserResponseDto;
import ru.practicum.dto.user.UserShortDto;
import ru.practicum.entity.User;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {
    User toUserModel(UserDto userModelDto);

    UserDto toUserDto(User user);

    List<UserDto> toUserDtoList(List<User> usersList);
}