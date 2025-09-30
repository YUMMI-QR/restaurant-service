package com.yummiqr.restaurant_service.dto.response;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressResponseDTO {
    private Long id;
    private String streetType;
    private String streetName;
    private Integer streetNumber;
    private String zipCode;
    private String city;
    private String country;
}
