package com.yummiqr.restaurant_service.controller;

import com.yummiqr.restaurant_service.dto.request.ProductDTO;
import com.yummiqr.restaurant_service.dto.response.ProductResponseDTO;
import com.yummiqr.restaurant_service.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts(){
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long id){
        return ResponseEntity.ok(productService.retrieveProduct(id));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ProductResponseDTO>> getProductsByCategory(@PathVariable Long categoryId){
        return ResponseEntity.ok(productService.findByCategory(categoryId));
    }

    @PostMapping("/{categoryId}")
    public ResponseEntity<ProductResponseDTO> createProduct(@Valid @RequestBody ProductDTO ProductDTO, @PathVariable Long categoryId){
        ProductResponseDTO created = productService.create(categoryId, ProductDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@Valid @RequestBody ProductDTO ProductDTO, @PathVariable Long id){
        return ResponseEntity.ok(productService.update(id, ProductDTO));
    }
}
