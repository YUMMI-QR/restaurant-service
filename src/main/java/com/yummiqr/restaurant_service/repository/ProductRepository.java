package com.yummiqr.restaurant_service.repository;

import com.yummiqr.restaurant_service.model.Product;
import com.yummiqr.restaurant_service.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryId(Long categoryId);
}
