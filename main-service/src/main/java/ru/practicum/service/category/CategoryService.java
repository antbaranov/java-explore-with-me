package ru.practicum.service.category;

import ru.practicum.entity.Category;

import java.util.List;

public interface CategoryService {

    Category create(Category category);

    void deleteById(Long catId);

    Category update(Long catId, Category category);

    List<Category> findCategories(int from, int size);

    Category findById(Long id);
}