package com.yummiqr.restaurant_service.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDTO {

    private Long id;

    @NotBlank(message = "El tipo de calle es obligatorio")
    private String streetType;

    @NotBlank(message = "El nombre de la calle es obligatorio")
    @Size(max = 40, message = "El nombre de la calle no puede superar 40 caracteres")
    private String streetName;

    @NotNull(message = "El número de la calle es obligatorio")
    private Integer streetNumber;

    @NotBlank(message = "El código postal es obligatorio")
    @Pattern(regexp = "\\d{5}", message = "El código postal debe tener 5 dígitos")
    private String zipCode;

    @NotBlank(message = "La ciudad es obligatoria")
    private String city;

    @NotBlank(message = "El país es obligatorio")
    private String country;
}
