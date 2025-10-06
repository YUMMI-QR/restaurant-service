package com.yummiqr.restaurant_service.service;

import com.yummiqr.restaurant_service.dto.request.CategoryDTO;
import com.yummiqr.restaurant_service.dto.request.RestaurantDTO;
import com.yummiqr.restaurant_service.dto.response.CategoryResponseDTO;
import com.yummiqr.restaurant_service.mapper.CategoryMapper;
import com.yummiqr.restaurant_service.model.Category;
import com.yummiqr.restaurant_service.model.Restaurant;
import com.yummiqr.restaurant_service.repository.CategoryRepository;
import com.yummiqr.restaurant_service.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper mapper;
    private final RestaurantRepository restaurantRepository;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper mapper, RestaurantRepository restaurantRepository) {
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
        this.restaurantRepository = restaurantRepository;
    }

    public List<CategoryResponseDTO> findAll(){
        return mapper.toResponseDTOList(categoryRepository.findAll());
    }

    public List<CategoryResponseDTO> findCategoriesByRestaurant(Long restaurantId){
        return mapper.toResponseDTOList(categoryRepository.findByRestaurantId(restaurantId));
    }

    public CategoryResponseDTO retrieveCategory(Long id){
        return mapper.toResponseDTO(categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Categoria no encontrada")));
    }

    public CategoryResponseDTO create(Long restaurantId, CategoryDTO categoryDTO){
        if(categoryRepository.existsByRestaurantIdAndName(restaurantId, categoryDTO.getName())){
            return mapper.toResponseDTO(mapper.toEntity(categoryDTO));
        }
        Restaurant restaurantDB = restaurantRepository.findById(restaurantId).orElseThrow(() ->
                new IllegalArgumentException("ID del restaurante no encontrado"));

        Category category = mapper.toEntity(categoryDTO);
        category.setRestaurant(restaurantDB);
        categoryRepository.save(category);
        restaurantDB.getCategories().add(category);
        restaurantRepository.save(restaurantDB);
        return mapper.toResponseDTO(category);

    }

    public CategoryResponseDTO update(Long categoryId, CategoryDTO categoryDTO){
        Category categoryDB = categoryRepository.findById(categoryId).orElseThrow(() -> new IllegalArgumentException("Categoria no encontrada"));
        categoryDB.setName(categoryDTO.getName());
        return mapper.toResponseDTO(categoryRepository.save(categoryDB));
    }
}
