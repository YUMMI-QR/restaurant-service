package com.yummiqr.restaurant_service.dto.response;

import com.yummiqr.restaurant_service.dto.request.RestaurantDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantTableResponseDTO {

    private Long id;
    private String qrCode;
    private Integer number;
    private RestaurantResponseDTO restaurant; // referencia al restaurante
}
