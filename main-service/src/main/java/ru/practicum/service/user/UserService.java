package ru.practicum.service.user;

import ru.practicum.entity.User;

import java.util.List;

public interface UserService {

    User create(User user);

    List<User> getUsers(List<Long> ids, int from, int size);

    User getById(Long userId);

    void deleteById(Long userId);
}
