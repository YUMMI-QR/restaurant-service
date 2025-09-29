package com.yummiqr.restaurant_service.dto;

import com.yummiqr.restaurant_service.model.Category;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {

    private Long id;

    @NotBlank(message = "{product.name.notblank}")
    @Size(max = 40, message = "{product.name.size}")
    private String name;

    @NotNull(message = "{product.price.notnull}")
    @DecimalMin(value = "0.01", message = "{product.price.decimalmin}")
    private Double price;

    @Size(max = 255, message = "{product.description.size}")
    private String description;

    @Valid
    private Category category; // referencia en lugar de objeto completo
}
