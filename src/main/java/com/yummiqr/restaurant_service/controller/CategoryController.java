package com.yummiqr.restaurant_service.controller;


import com.yummiqr.restaurant_service.dto.request.CategoryDTO;
import com.yummiqr.restaurant_service.dto.response.CategoryResponseDTO;
import com.yummiqr.restaurant_service.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories(){
        return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.retrieveCategory(id));
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<CategoryResponseDTO>> findByRestaurant(@PathVariable Long restaurantId){
        return ResponseEntity.ok(categoryService.findCategoriesByRestaurant(restaurantId));
    }

    @PostMapping("/restaurant/{restaurantId}")
    public ResponseEntity<CategoryResponseDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable Long restaurantId){
        CategoryResponseDTO created = categoryService.create(restaurantId, categoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable Long id){
        return ResponseEntity.ok(categoryService.update(id, categoryDTO));
    }
}
