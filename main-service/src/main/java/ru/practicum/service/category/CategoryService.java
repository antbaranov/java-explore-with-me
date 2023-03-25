package ru.practicum.service.category;

import ru.practicum.dto.category.CategoryResponseDto;
import ru.practicum.dto.category.NewCategoryDto;
import ru.practicum.entity.Category;

import java.util.List;

public interface CategoryService {

    CategoryResponseDto create(NewCategoryDto newCategoryDto);

    void deleteById(Long catId);

    CategoryResponseDto update(Long catId, NewCategoryDto newCategoryDto);

    List<Category> findCategories(int from, int size);

    Category findById(Long id);
}