package ru.practicum.service.category;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.dto.category.CategoryResponseDto;
import ru.practicum.dto.category.NewCategoryDto;
import ru.practicum.entity.Category;
import ru.practicum.exception.NotFoundException;
import ru.practicum.mapper.CategoryMapper;
import ru.practicum.repository.CategoryRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    @Override
    public CategoryResponseDto create(NewCategoryDto newCategoryDto) {
        Category category = categoryMapper.toCategory(newCategoryDto);
        return CategoryMapper.toCategoryResponseDto(categoryRepository.save(category));
    }

    @Override
    public void deleteById(Long catId) {
        findById(catId);
        categoryRepository.deleteById(catId);
    }

    @Override
    public CategoryResponseDto update(Long catId, NewCategoryDto newCategoryDto) {
        Category category = categoryMapper.toCategory(newCategoryDto);
        category.setId(catId);
        return CategoryMapper.toCategoryResponseDto(categoryRepository.save(category));
    }

    @Override
    public List<Category> findCategories(int from, int size) {
        return categoryRepository.findAll(PageRequest.of(from, size))
                .toList();
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Category with id=" + id));
    }
}