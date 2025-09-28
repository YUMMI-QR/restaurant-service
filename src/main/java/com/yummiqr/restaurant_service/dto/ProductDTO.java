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

    @NotBlank(message = "El nombre del producto es obligatorio")
    @Size(max = 40, message = "El nombre del producto no puede superar 40 caracteres")
    private String name;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor que 0")
    private Double price;

    @Size(max = 255, message = "La descripción no puede superar 255 caracteres")
    private String description;

    @Valid
    private Category category; // referencia en lugar de objeto completo
}
