package com.yummiqr.restaurant_service.service;

import com.yummiqr.restaurant_service.dto.request.RestaurantDTO;
import com.yummiqr.restaurant_service.dto.response.AddressResponseDTO;
import com.yummiqr.restaurant_service.dto.response.RestaurantResponseDTO;
import com.yummiqr.restaurant_service.mapper.AddressMapper;
import com.yummiqr.restaurant_service.mapper.RestaurantMapper;
import com.yummiqr.restaurant_service.model.Address;
import com.yummiqr.restaurant_service.model.Restaurant;
import com.yummiqr.restaurant_service.repository.AddressRepository;
import com.yummiqr.restaurant_service.repository.RestaurantRepository;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepository repository;
    private final AddressRepository addressRepository;
    private final RestaurantMapper mapper;
    private final AddressMapper addressMapper;
    private final MessageSource messageSource;
    private final AddressService addressService;

    public RestaurantService(RestaurantRepository repository, AddressRepository addressRepository,
                             RestaurantMapper mapper, AddressMapper addressMapper, MessageSource messageSource, AddressService addressService) {
        this.repository = repository;
        this.mapper = mapper;
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
        this.messageSource = messageSource;
        this.addressService = addressService;
    }

    public List<RestaurantResponseDTO> findAll(){
        return mapper.toResponseDTOList(repository.findAll());
    }

    public RestaurantResponseDTO retrieveRestaurant(String nif){
        return repository.findByNif(nif)
                .map(mapper::toResponseDTO)
                .orElseThrow(() -> new IllegalArgumentException(
                        messageSource.getMessage(
                                "restaurant.nif.notfound",
                                new Object[]{nif},
                                LocaleContextHolder.getLocale()
                        )
                ));
    }

    public RestaurantResponseDTO retrieveRestaurantById(Long id){
        return repository.findById(id)
                .map(mapper::toResponseDTO)
                .orElseThrow(() -> new IllegalArgumentException(
                        messageSource.getMessage(
                                "El restaurante no existe",
                                new Object[]{id},
                                LocaleContextHolder.getLocale()
                        )
                ));
    }

    public RestaurantResponseDTO create(RestaurantDTO restaurantDTO){
        if(repository.existsByNif(restaurantDTO.getNif())){
            return mapper.toResponseDTO(mapper.toEntity(restaurantDTO));
        }else{
            Address address = addressRepository.save(addressMapper.toEntity(restaurantDTO.getAddress()));
            Restaurant restaurant = mapper.toEntity(restaurantDTO);
            restaurant.setAddress(address);
            Restaurant restaurantSaved = repository.save(restaurant);
            return mapper.toResponseDTO(restaurantSaved);
        }
    }

    public RestaurantResponseDTO update(String nif, RestaurantDTO restaurantDTO){

        Restaurant restaurantDB = repository.findByNif(nif).orElseThrow( () -> new IllegalArgumentException(
                messageSource.getMessage(
                        "restaurant.nif.notfound",
                        new Object[]{nif},
                        LocaleContextHolder.getLocale()
                )
        ));

        restaurantDB.setNif(restaurantDTO.getNif());
        restaurantDB.setName(restaurantDTO.getName());
        restaurantDB.setEmail(restaurantDTO.getEmail());
        restaurantDB.setTaxPercent(restaurantDTO.getTaxPercent());
        restaurantDB.setFeePercent(restaurantDTO.getFeePercent());

        Address address = addressMapper.toEntity(addressService.create(restaurantDTO.getAddress()));
        restaurantDB.setAddress(address);
        restaurantDB = repository.save(restaurantDB);

        return mapper.toResponseDTO(restaurantDB);

    }
}
