package com.yummiqr.restaurant_service.service;

import com.yummiqr.restaurant_service.dto.request.RestaurantDTO;
import com.yummiqr.restaurant_service.dto.response.RestaurantResponseDTO;
import com.yummiqr.restaurant_service.mapper.RestaurantMapper;
import com.yummiqr.restaurant_service.model.Restaurant;
import com.yummiqr.restaurant_service.repository.RestaurantRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepository repository;
    private final RestaurantMapper mapper;

    public RestaurantService(RestaurantRepository repository, RestaurantMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<RestaurantResponseDTO> findAll(){
        return mapper.toResponseDTOList(repository.findAll());
    }

    public RestaurantResponseDTO retrieveRestaurant(String nif){
        Restaurant restaurant = repository.findByNif(nif).orElseThrow(() -> new IllegalArgumentException("Restaurante con NIF " + nif + " no encontrado"));
        return mapper.toResponseDTO(restaurant);
    }

    public RestaurantResponseDTO create(RestaurantDTO restaurantDTO){
        if(repository.existsByNif(restaurantDTO.getNif())){
            return mapper.toResponseDTO(mapper.toEntity(restaurantDTO));
        }else{
            Restaurant saved = repository.save(mapper.toEntity(restaurantDTO));
            return mapper.toResponseDTO(saved);
        }
    }
}
