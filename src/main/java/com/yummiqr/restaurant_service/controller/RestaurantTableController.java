package com.yummiqr.restaurant_service.controller;

import com.yummiqr.restaurant_service.dto.request.RestaurantTableDTO;
import com.yummiqr.restaurant_service.dto.response.RestaurantTableResponseDTO;
import com.yummiqr.restaurant_service.service.RestaurantTableService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurantTables")
@RequiredArgsConstructor
public class RestaurantTableController {

    private final RestaurantTableService restaurantTableService;

    @GetMapping
    public ResponseEntity<List<RestaurantTableResponseDTO>> findAll(){
        return ResponseEntity.ok(restaurantTableService.findAll());
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<RestaurantTableResponseDTO>> findByRestaurantId(@PathVariable Long restaurantId){
        return ResponseEntity.ok(restaurantTableService.findByRestaurantId(restaurantId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantTableResponseDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(restaurantTableService.findById(id));
    }

    @PostMapping("/restaurant/{restaurantId}")
    public ResponseEntity<RestaurantTableResponseDTO> create(@Valid @RequestBody RestaurantTableDTO restaurantTableDTO,
                                                             @PathVariable Long restaurantId){
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantTableService.create(restaurantId, restaurantTableDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantTableResponseDTO> update(@Valid @RequestBody RestaurantTableDTO restaurantTableDTO,
                                                             @PathVariable Long id){
        return ResponseEntity.ok(restaurantTableService.update(id, restaurantTableDTO));
    }
}
