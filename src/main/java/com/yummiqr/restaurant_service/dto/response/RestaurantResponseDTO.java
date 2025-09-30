package com.yummiqr.restaurant_service.dto.response;

import com.yummiqr.restaurant_service.dto.request.AddressDTO;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantResponseDTO {

    private Long id; // opcional en POST, obligatorio en PUT
    private String name;
    private String nif;
    private String email;
    private BigDecimal taxPercent;
    private BigDecimal feePercent;
    private LocalDateTime creationDate;
    private AddressResponseDTO address; // composición
}
