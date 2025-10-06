package com.yummiqr.restaurant_service.dto.response;

import com.yummiqr.restaurant_service.model.Category;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDTO {
    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private CategoryResponseDTO category; // referencia en lugar de objeto completo
}
