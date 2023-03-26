package ru.practicum.service.category;

import ru.practicum.dto.category.CategoryResponseDto;
import ru.practicum.dto.category.NewCategoryDto;
import ru.practicum.entity.Category;

import java.util.List;

public interface CategoryService {

    CategoryResponseDto create(NewCategoryDto newCategoryDto);

    void deleteById(Long catId);

    CategoryResponseDto update(Long catId, NewCategoryDto newCategoryDto);

    List<CategoryResponseDto> findCategories(int from, int size);

    CategoryResponseDto findById(Long id);

    Category findByIdCreate(Long id);
}