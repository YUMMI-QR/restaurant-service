package com.yummiqr.restaurant_service.service;

import com.yummiqr.restaurant_service.dto.request.RestaurantTableDTO;
import com.yummiqr.restaurant_service.dto.response.RestaurantTableResponseDTO;
import com.yummiqr.restaurant_service.mapper.RestaurantTableMapper;
import com.yummiqr.restaurant_service.model.Category;
import com.yummiqr.restaurant_service.model.Restaurant;
import com.yummiqr.restaurant_service.model.RestaurantTable;
import com.yummiqr.restaurant_service.repository.RestaurantRepository;
import com.yummiqr.restaurant_service.repository.RestaurantTableRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantTableService {

    private final RestaurantTableRepository repository;
    private final RestaurantTableMapper mapper;
    private final RestaurantRepository restaurantRepository;

    public RestaurantTableService(RestaurantTableRepository repository, RestaurantTableMapper mapper, RestaurantRepository restaurantRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.restaurantRepository = restaurantRepository;
    }

    public List<RestaurantTableResponseDTO> findAll(){
        return mapper.toResponseDTOList(repository.findAll());
    }

    public List<RestaurantTableResponseDTO> findByRestaurantId(Long restaurantId){
        return mapper.toResponseDTOList(repository.findByRestaurantId(restaurantId));
    }

    public RestaurantTableResponseDTO findById(Long id){
        return mapper.toResponseDTO(repository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("No se ha encontrado ninguna mesa con el ID " + id)));
    }

    public RestaurantTableResponseDTO findByQR(String qrCode){
        return mapper.toResponseDTO(repository.findByQrCode(qrCode).orElseThrow(()->
                new IllegalArgumentException("No se ha encontrado ninguna mesa con el QR " + qrCode)));
    }

    public RestaurantTableResponseDTO create(Long restaurantId, RestaurantTableDTO restaurantTableDTO){

        if(repository.existsByRestaurantIdAndNumber(restaurantId, restaurantTableDTO.getNumber())){
            return mapper.toResponseDTO(mapper.toEntity(restaurantTableDTO));
        }
        Restaurant restaurantDB = restaurantRepository.findById(restaurantId).orElseThrow(() ->
                new IllegalArgumentException("ID del restaurante no encontrado"));

        RestaurantTable restaurantTable = mapper.toEntity(restaurantTableDTO);
        restaurantTable.setRestaurant(restaurantDB);
        repository.save(restaurantTable);
        restaurantDB.getTables().add(restaurantTable);
        restaurantRepository.save(restaurantDB);
        return mapper.toResponseDTO(restaurantTable);

    }

    public RestaurantTableResponseDTO update(Long id, RestaurantTableDTO restaurantTableDTO){
        RestaurantTable restaurantTable = repository.findById(id).orElseThrow(()->
                new IllegalArgumentException("No se ha encontrado mesa con el ID " + id));

        restaurantTable.setNumber(restaurantTableDTO.getNumber());
        restaurantTable.setQrCode(restaurantTable.getQrCode());

        return mapper.toResponseDTO(repository.save(restaurantTable));
    }
}
