package com.yummiqr.restaurant_service.repository;

import com.yummiqr.restaurant_service.model.Category;
import com.yummiqr.restaurant_service.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByRestaurantId(Long restaurantId);
}
