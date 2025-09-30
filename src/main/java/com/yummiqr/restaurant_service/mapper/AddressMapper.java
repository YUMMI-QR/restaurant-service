package com.yummiqr.restaurant_service.mapper;

import com.yummiqr.restaurant_service.dto.request.AddressDTO;
import com.yummiqr.restaurant_service.dto.response.AddressResponseDTO;
import com.yummiqr.restaurant_service.model.Address;
import org.springframework.stereotype.Component;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AddressMapper {

    public Address toEntity(AddressDTO dto){
        if (dto==null){
            return null;
        }
        return Address.builder()
                .streetType(dto.getStreetType())
                .streetName(dto.getStreetName())
                .streetNumber(dto.getStreetNumber())
                .zipCode(dto.getZipCode())
                .city(dto.getCity())
                .country(dto.getCountry())
                .build();
    }
    public AddressResponseDTO toResponseDTO(Address address){
        if (address == null) return null;

        return AddressResponseDTO.builder()
                .id(address.getId())
                .streetType(address.getStreetType())
                .streetName(address.getStreetName())
                .streetNumber(address.getStreetNumber())
                .zipCode(address.getZipCode())
                .city(address.getCity())
                .country(address.getCountry())
                .build();

    }
    public List<AddressResponseDTO> toResponseDTOList(List<Address> addressList){
        if(addressList == null){
            return Collections.emptyList();
        }
        return addressList.stream()
                .map(this::toResponseDTO).collect(Collectors.toList());

    }
}
