package com.yummiqr.restaurant_service.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantDTO {

    private Long id; // opcional en POST, obligatorio en PUT

    @NotBlank(message = "El nombre del restaurante es obligatorio")
    @Size(max = 40, message = "El nombre no puede superar 40 caracteres")
    private String name;

    @NotBlank(message = "El NIF es obligatorio")
    @Size(max = 9, message = "El NIF no puede superar los 9 caracteres")
    private String nif;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email no tiene un formato válido")
    private String email;

    @DecimalMin(value = "0.0", inclusive = false, message = "El impuesto debe ser mayor que 0")
    @DecimalMax(value = "100.0", message = "El impuesto no puede superar el 100%")
    private Double taxPercent;

    @DecimalMin(value = "0.0", message = "La comisión no puede ser negativa")
    @DecimalMax(value = "100.0", message = "La comisión no puede superar el 100%")
    private Double feePercent;

    @Valid
    private AddressDTO address; // composición
}
