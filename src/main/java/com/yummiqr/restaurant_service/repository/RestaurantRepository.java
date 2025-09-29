package com.yummiqr.restaurant_service.repository;

import com.yummiqr.restaurant_service.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    Optional<Restaurant> findByEmail(String email);
    boolean existsByNif(String nif);

}
