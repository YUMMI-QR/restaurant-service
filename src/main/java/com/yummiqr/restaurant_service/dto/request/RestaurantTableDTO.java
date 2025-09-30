package com.yummiqr.restaurant_service.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantTableDTO {

    private Long id;

    @NotBlank(message = "{table.qr.notblank}")
    @Size(max = 40, message = "{table.qr.size}")
    private String qrCode;

    @NotNull(message = "{table.number.notnull}")
    @Min(value = 1, message = "{table.number.min}")
    private Integer number;

    @Valid
    private RestaurantDTO restaurantDTO; // referencia al restaurante
}
