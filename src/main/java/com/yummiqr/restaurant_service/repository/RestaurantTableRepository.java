package com.yummiqr.restaurant_service.repository;

import com.yummiqr.restaurant_service.model.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantTableRepository extends JpaRepository<RestaurantTable, Long> {

    Optional<RestaurantTable> findByQrCode(String qrCode);
    List<RestaurantTable> findByRestaurantId(Long restaurantId);
    boolean existsByRestaurantIdAndNumber(Long restaurantId, Integer number);
}
