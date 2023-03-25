package ru.practicum.mapper;

import org.mapstruct.Mapper;
import ru.practicum.dto.user.*;
import ru.practicum.entity.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserIncomeDto userIncomeDto);
    UserDto toUserDto(User user);
    UserResponseDto toUserResponseDto(User user);

    static UserShortDto toUserShortDto(User user) {
        return UserShortDto.builder()
                .id(user.getId())
                .name(user.getName())
                .build();
    }

    List<UserResponseDto> toUserResponseDtoList(List<User> users);
}