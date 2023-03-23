package ru.practicum.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.dto.category.CategoryResponseDto;
import ru.practicum.dto.category.NewCategoryDto;
import ru.practicum.mapper.CategoryMapper;
import ru.practicum.service.category.CategoryService;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@Validated
@RestController
@RequestMapping(path = "/admin/categories")
@RequiredArgsConstructor
@Slf4j
public class AdminCategoryController {

    private final CategoryService categoryService;

    private final CategoryMapper categoryMapper;

    /**
     * Добавление новой категории
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponseDto create(@Valid @RequestBody NewCategoryDto newCategoryDto) {
        log.info("Create {}", newCategoryDto.toString());
        return CategoryMapper.toCategoryResponseDto(
                categoryService.create(categoryMapper.toCategory(newCategoryDto)));
    }

    /**
     * Удаление категории
     */
    @DeleteMapping("{catId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable @Min(0) Long catId) {
        log.info("Delete by id={}", catId);
        categoryService.deleteById(catId);
    }

    /**
     * Обновление категории
     */
    @PatchMapping("{catId}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryResponseDto update(@Valid @RequestBody NewCategoryDto newCategoryDto,
                                      @PathVariable @Min(0) Long catId) {
        log.info("Update by id={}, for {}", catId, newCategoryDto.toString());
        return CategoryMapper.toCategoryResponseDto(
                categoryService.update(catId, categoryMapper.toCategory(newCategoryDto)));
    }
}