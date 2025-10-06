package com.yummiqr.restaurant_service.mapper;

import com.yummiqr.restaurant_service.dto.request.CategoryDTO;
import com.yummiqr.restaurant_service.dto.response.CategoryResponseDTO;
import com.yummiqr.restaurant_service.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CategoryMapper {

    private final RestaurantMapper restaurantMapper;

    public Category toEntity(CategoryDTO categoryDTO){
        return categoryDTO == null ? null : Category.builder().id(categoryDTO.getId()).name(categoryDTO.getName())
                        .restaurant(restaurantMapper.toEntity(categoryDTO.getRestaurantDTO())).build();
    }

    public CategoryResponseDTO toResponseDTO(Category category){
        return category == null ? null : CategoryResponseDTO.builder().id(category.getId()).name(category.getName())
                        .restaurantResponseDTO(restaurantMapper.toResponseDTO(category.getRestaurant())).build();
    }

    public List<CategoryResponseDTO> toResponseDTOList(List<Category> categories){
        return categories == null ? null : categories.stream().map(this::toResponseDTO).collect(Collectors.toList());
    }
}
