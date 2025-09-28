package com.yummiqr.restaurant_service.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDTO {

    private Long id;

    @NotBlank(message = "{category.name.notblank}")
    @Size(max = 40, message = "{category.name.size}")
    private String name;

    @Valid
    private RestaurantDTO restaurantDTO; // referencia al restaurante
}
