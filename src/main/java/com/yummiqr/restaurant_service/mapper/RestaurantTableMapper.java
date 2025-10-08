package com.yummiqr.restaurant_service.mapper;

import com.yummiqr.restaurant_service.dto.request.RestaurantTableDTO;
import com.yummiqr.restaurant_service.dto.response.RestaurantTableResponseDTO;
import com.yummiqr.restaurant_service.model.RestaurantTable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RestaurantTableMapper {

    private final RestaurantMapper restaurantMapper;

    public RestaurantTable toEntity(RestaurantTableDTO restaurantTableDTO){
        return restaurantTableDTO == null ? null :
                RestaurantTable.builder().id(restaurantTableDTO.getId()).qrCode(restaurantTableDTO.getQrCode())
                        .number(restaurantTableDTO.getNumber())
                        .restaurant(restaurantMapper.toEntity(restaurantTableDTO.getRestaurantDTO())).build();
    }
    public RestaurantTableResponseDTO toResponseDTO(RestaurantTable restaurantTable){
        return restaurantTable == null ? null :
                RestaurantTableResponseDTO.builder().id(restaurantTable.getId()).number(restaurantTable.getNumber())
                        .qrCode(restaurantTable.getQrCode())
                        .restaurant(restaurantMapper.toResponseDTO(restaurantTable.getRestaurant())).build();
    }
    public List<RestaurantTableResponseDTO> toResponseDTOList(List<RestaurantTable> restaurantTables){
        return restaurantTables == null ? null : restaurantTables.stream().map(this::toResponseDTO).collect(Collectors.toList());
    }
}
