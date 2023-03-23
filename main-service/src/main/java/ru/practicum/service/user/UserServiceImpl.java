package ru.practicum.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.entity.User;
import ru.practicum.exception.NotFoundException;
import ru.practicum.repository.UserRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User create(User user) {

        return userRepository.save(user);
    }

    @Override
    public List<User> getUsers(List<Long> ids, int from, int size) {
        return userRepository.findByIdIn(ids, PageRequest.of(from, size));
    }

    @Override
    public User getById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User with id=" + userId));
    }

    @Override
    public void deleteById(Long userId) {
        getById(userId);
        userRepository.deleteById(userId);
    }
}
