package com.yummiqr.restaurant_service.mapper;

import com.yummiqr.restaurant_service.dto.request.CategoryDTO;
import com.yummiqr.restaurant_service.dto.response.CategoryResponseDTO;
import com.yummiqr.restaurant_service.model.Category;
import org.mapstruct.Mapper;

import java.util.List;


public class CategoryMapper {

    public Category toEntity(CategoryDTO categoryDTO){
        return null;
    }
    public CategoryResponseDTO toResponseDTO(Category category){
        return null;
    }
    public List<CategoryResponseDTO> toResponseDTOList(List<Category> categories){
        return null;
    }
}
