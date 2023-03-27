package ru.practicum.mapper;

import org.mapstruct.Mapper;
import ru.practicum.dto.user.UserDto;
import ru.practicum.dto.user.UserIncomeDto;
import ru.practicum.dto.user.UserResponseDto;
import ru.practicum.dto.user.UserShortDto;
import ru.practicum.entity.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserIncomeDto userIncomeDto);

    UserDto toUserDto(User user);

    static UserShortDto toUserShortDto(User user) {
        return UserShortDto.builder()
                .id(user.getId())
                .name(user.getName())
                .build();
    }

    List<UserResponseDto> toUserResponseDtoList(List<User> users);
}