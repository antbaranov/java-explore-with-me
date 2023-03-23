package ru.practicum.controller.pub;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.dto.category.CategoryResponseDto;
import ru.practicum.mapper.CategoryMapper;
import ru.practicum.service.category.CategoryService;

import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Slf4j
public class PublicCategoryController {

    private final CategoryService categoryService;

    private final CategoryMapper categoryMapper;

    /**
     * Получение категорий
     */
    @GetMapping
    public List<CategoryResponseDto> getAll(
            @RequestParam(defaultValue = "0") @Min(0) int from,
            @RequestParam(defaultValue = "10") @Min(1) int size) {
        log.info("GET categories by from={}, size={}", from, size);
        return categoryMapper.toCategoryResponseDtoList(
                categoryService.findCategories(from, size));
    }

    /**
     * Получение информации о категории по её идентификатору
     */
    @GetMapping("{catId}")
    public CategoryResponseDto getById(@PathVariable @Min(0) Long catId) {
        log.info("GET category by id={}", catId);
        return CategoryMapper.toCategoryResponseDto(
                categoryService.findById(catId));
    }
}