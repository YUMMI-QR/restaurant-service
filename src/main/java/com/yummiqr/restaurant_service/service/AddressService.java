package com.yummiqr.restaurant_service.service;

import com.yummiqr.restaurant_service.dto.request.AddressDTO;
import com.yummiqr.restaurant_service.dto.request.RestaurantDTO;
import com.yummiqr.restaurant_service.dto.response.AddressResponseDTO;
import com.yummiqr.restaurant_service.dto.response.RestaurantResponseDTO;
import com.yummiqr.restaurant_service.mapper.AddressMapper;
import com.yummiqr.restaurant_service.model.Address;
import com.yummiqr.restaurant_service.model.Restaurant;
import com.yummiqr.restaurant_service.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository repository;
    private final AddressMapper mapper;

    public AddressService(AddressRepository repository, AddressMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<AddressResponseDTO> findAll(){
        return mapper.toResponseDTOList(repository.findAll());
    }

    public AddressResponseDTO retrieveRestaurant(Long id){
        Address address = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Direccion no encontrada"));
        return mapper.toResponseDTO(address);
    }

    public AddressResponseDTO create(AddressDTO addressDTO){
            Address saved = repository.save(mapper.toEntity(addressDTO));
            return mapper.toResponseDTO(saved);
    }

}
