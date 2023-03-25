package ru.practicum.service.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.dto.user.UserDto;
import ru.practicum.dto.user.UserIncomeDto;
import ru.practicum.entity.User;
import ru.practicum.exception.NotFoundException;
import ru.practicum.mapper.UserMapper;
import ru.practicum.repository.UserRepository;

import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserDto create(UserIncomeDto userIncomeDto) {
        User user = userRepository.save(userMapper.toUser(userIncomeDto));
        log.info("Creat user id = {} ", user.getId());
        return userMapper.toUserDto(user);
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
