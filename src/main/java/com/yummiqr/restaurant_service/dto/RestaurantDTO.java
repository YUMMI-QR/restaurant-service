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

    @NotBlank(message = "{restaurant.name.notblank}")
    @Size(max = 40, message = "{restaurant.name.size}")
    private String name;

    @NotBlank(message = "{restaurant.nif.notblank}")
    @Size(max = 9, message = "{restaurant.nif.size}")
    private String nif;

    @NotBlank(message = "{restaurant.email.notblank}")
    @Email(message = "{restaurant.email.email}")
    private String email;

    @DecimalMin(value = "0.0", inclusive = false, message = "{restaurant.taxpercent.decimalmin}")
    @DecimalMax(value = "100.0", message = "{restaurant.taxpercent.decimalmax}")
    private Double taxPercent;

    @DecimalMin(value = "0.0", message = "{restaurant.feepercent.decimalmin}")
    @DecimalMax(value = "100.0", message = "{restaurant.feepercent.decimal.max}")
    private Double feePercent;

    @Valid
    private AddressDTO address; // composición
}
