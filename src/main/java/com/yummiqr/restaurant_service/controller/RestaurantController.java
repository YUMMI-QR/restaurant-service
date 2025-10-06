package com.yummiqr.restaurant_service.controller;

import com.yummiqr.restaurant_service.dto.request.RestaurantDTO;
import com.yummiqr.restaurant_service.dto.response.RestaurantResponseDTO;
import com.yummiqr.restaurant_service.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    // Crear restaurante
    @PostMapping
    public ResponseEntity<RestaurantResponseDTO> createRestaurant(@Valid @RequestBody RestaurantDTO restaurantDTO) {
        RestaurantResponseDTO created = restaurantService.create(restaurantDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // Obtener todos los restaurantes
    @GetMapping
    public ResponseEntity<List<RestaurantResponseDTO>> getAllRestaurants() {
        return ResponseEntity.ok(restaurantService.findAll());
    }

//    // Obtener restaurante por ID
//    @GetMapping("/{id}")
//    public ResponseEntity<RestaurantResponseDTO> getRestaurantById(@PathVariable Long id) {
//        return ResponseEntity.ok(restaurantService.getRestaurantById(id));
//    }
//
    // Obtener restaurante por NIF
    @GetMapping("/nif/{nif}")
    public ResponseEntity<RestaurantResponseDTO> getRestaurantByNif(@PathVariable String nif) {
        return ResponseEntity.ok(restaurantService.retrieveRestaurant(nif));
    }
//
    // Actualizar restaurante
    @PutMapping("/nif/{nif}")
    public ResponseEntity<RestaurantResponseDTO> updateRestaurant(
            @PathVariable String nif,
            @Valid @RequestBody RestaurantDTO restaurantDTO) {
        return ResponseEntity.ok(restaurantService.update(nif, restaurantDTO));
    }
//
//    // Eliminar restaurante
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id) {
//        restaurantService.deleteRestaurant(id);
//        return ResponseEntity.noContent().build();
//    }
}
