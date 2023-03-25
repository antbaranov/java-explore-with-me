package ru.practicum.mapper;

import org.mapstruct.Mapper;
import ru.practicum.dto.category.CategoryResponseDto;
import ru.practicum.dto.category.NewCategoryDto;
import ru.practicum.entity.Category;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toCategory(NewCategoryDto dto);
   // CategoryResponseDto toCategoryResponseDto(Category category);

    static CategoryResponseDto toCategoryResponseDto(Category category) {
        return CategoryResponseDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    List<CategoryResponseDto> toCategoryResponseDtoList(List<Category> categories);
}