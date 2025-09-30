package com.yummiqr.restaurant_service.mapper;

import com.yummiqr.restaurant_service.dto.request.RestaurantDTO;
import com.yummiqr.restaurant_service.dto.response.RestaurantResponseDTO;
import com.yummiqr.restaurant_service.model.Restaurant;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestaurantMapper {

    private final AddressMapper addressMapper;

    public RestaurantMapper(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    public Restaurant toEntity(RestaurantDTO dto){
        if(dto == null){
            return null;
        }
        return Restaurant.builder()
                .name(dto.getName())
                .nif(dto.getNif())
                .email(dto.getEmail())
                .taxPercent(dto.getTaxPercent())
                .feePercent(dto.getFeePercent())
                .address(addressMapper.toEntity(dto.getAddress()))
                .build();

    }
    public RestaurantResponseDTO toResponseDTO(Restaurant restaurant){
        if(restaurant == null){
            return null;
        }
        return RestaurantResponseDTO.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .nif(restaurant.getNif())
                .email(restaurant.getEmail())
                .taxPercent(restaurant.getTaxPercent())
                .feePercent(restaurant.getFeePercent())
                .creationDate(restaurant.getCreationDate())
                .address(addressMapper.toResponseDTO(restaurant.getAddress()))
                .build();

    }
    public List<RestaurantResponseDTO> toResponseDTOList(List<Restaurant> restaurants){
        if(restaurants == null){
            return null;
        }
        return restaurants.stream()
                .map(this::toResponseDTO).collect(Collectors.toList());
    }
}
