package com.yummiqr.restaurant_service.service;

import com.yummiqr.restaurant_service.dto.request.RestaurantDTO;
import com.yummiqr.restaurant_service.dto.response.RestaurantResponseDTO;
import com.yummiqr.restaurant_service.mapper.AddressMapper;
import com.yummiqr.restaurant_service.mapper.RestaurantMapper;
import com.yummiqr.restaurant_service.model.Address;
import com.yummiqr.restaurant_service.model.Restaurant;
import com.yummiqr.restaurant_service.repository.AddressRepository;
import com.yummiqr.restaurant_service.repository.RestaurantRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepository repository;
    private final AddressRepository addressRepository;
    private final RestaurantMapper mapper;
    private final AddressMapper addressMapper;

    public RestaurantService(RestaurantRepository repository, AddressRepository addressRepository,
                             RestaurantMapper mapper, AddressMapper addressMapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }

    public List<RestaurantResponseDTO> findAll(){
        return mapper.toResponseDTOList(repository.findAll());
    }

    public RestaurantResponseDTO retrieveRestaurant(String nif){
        Restaurant restaurant = repository.findByNif(nif).orElseThrow(() -> new IllegalArgumentException("Restaurante" +
                " con NIF " + nif + " no encontrado"));
        return mapper.toResponseDTO(restaurant);
    }

    public RestaurantResponseDTO create(RestaurantDTO restaurantDTO){
        if(repository.existsByNif(restaurantDTO.getNif())){
            return mapper.toResponseDTO(mapper.toEntity(restaurantDTO));
        }else{

            Restaurant restaurantSaved = repository.save(mapper.toEntity(restaurantDTO));
            restaurantSaved.setAddress(addressMapper.toEntity(restaurantDTO.getAddress()));
            return mapper.toResponseDTO(restaurantSaved);
        }
    }
}
