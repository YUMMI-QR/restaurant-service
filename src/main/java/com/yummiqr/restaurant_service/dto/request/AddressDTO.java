package com.yummiqr.restaurant_service.dto.request;


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

    @NotBlank(message = "{address.streettype.notblank}")
    private String streetType;

    @NotBlank(message = "{address.streetname.notblank}")
    @Size(max = 40, message = "{address.streetname.size}")
    private String streetName;

    @NotNull(message = "{address.streetnumber.notnull}")
    private Integer streetNumber;

    @NotBlank(message = "{address.zipcode.notblank}")
    @Pattern(regexp = "\\d{5}", message = "{address.zipcode.pattern}")
    private String zipCode;

    @NotBlank(message = "{address.city.notblank}")
    private String city;

    @NotBlank(message = "{address.country.notblank}")
    private String country;
}
