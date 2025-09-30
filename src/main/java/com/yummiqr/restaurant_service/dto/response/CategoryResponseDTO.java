package com.yummiqr.restaurant_service.dto.response;

import com.yummiqr.restaurant_service.dto.request.RestaurantDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryResponseDTO {

    private Long id;
    private String name;
    private RestaurantDTO restaurantDTO; // referencia al restaurante
}
