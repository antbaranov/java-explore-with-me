package ru.practicum.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.dto.user.UserDto;
import ru.practicum.dto.user.UserIncomeDto;
import ru.practicum.dto.user.UserResponseDto;
import ru.practicum.service.user.UserService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
@RequestMapping("/admin/users")
@RequiredArgsConstructor
@Slf4j
public class AdminUserController {

    private final UserService userService;

    /**
     * Добавление нового пользователя
     */

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public UserDto create(@Valid @RequestBody UserIncomeDto userIncomeDto) {
        return userService.create(userIncomeDto);
    }

    /**
     * Возвращает информацию обо всех пользователях (учитываются параметры ограничения выборки),
     * либо о конкретных (учитываются указанные идентификаторы)
     */
    @GetMapping
    public List<UserResponseDto> getUsersByParameters(
            @RequestParam(required = false) List<Long> ids,
            @RequestParam(defaultValue = "0") @Min(0) int from,
            @RequestParam(defaultValue = "10") @Min(1) int size) {
        log.info("GET users ids={}, from={}, size={}", ids, from, size);
        return userService.getUsers(ids, from, size);
    }

    /**
     * Удаление пользователя
     */
    @DeleteMapping("{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable @Min(0) Long userId) {
        log.info("Delete by id={}", userId);
        userService.deleteById(userId);
    }
}