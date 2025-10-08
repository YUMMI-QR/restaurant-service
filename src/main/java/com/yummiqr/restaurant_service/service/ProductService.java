package com.yummiqr.restaurant_service.service;

import com.yummiqr.restaurant_service.dto.request.ProductDTO;
import com.yummiqr.restaurant_service.dto.response.ProductResponseDTO;
import com.yummiqr.restaurant_service.mapper.ProductMapper;
import com.yummiqr.restaurant_service.model.Category;
import com.yummiqr.restaurant_service.model.Product;
import com.yummiqr.restaurant_service.model.Restaurant;
import com.yummiqr.restaurant_service.repository.CategoryRepository;
import com.yummiqr.restaurant_service.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper mapper;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, ProductMapper mapper, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.mapper = mapper;
        this.categoryRepository = categoryRepository;
    }

    public List<ProductResponseDTO> findAll(){
        return mapper.toResponseDTOList(productRepository.findAll());
    }

    public List<ProductResponseDTO> findByCategory(Long categoryId){
        return mapper.toResponseDTOList(productRepository.findByCategoryId(categoryId));
    }

    public ProductResponseDTO retrieveProduct(Long id){
        return mapper.toResponseDTO(productRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("No se ha encontrado producto ID " + id)));
    }

    public ProductResponseDTO create(Long categoryId, ProductDTO productDTO){
        if(productRepository.existsByCategoryIdAndName(categoryId, productDTO.getName())){
            return mapper.toResponseDTO(mapper.toEntity(productDTO));
        }

        Category categoryDB = categoryRepository.findById(categoryId).orElseThrow(() ->
                new IllegalArgumentException("No existe la categoria con ID " + categoryId));

        Product product = mapper.toEntity(productDTO);
        product.setCategory(categoryDB);
        productRepository.save(product);
        categoryDB.getProducts().add(product);
        categoryRepository.save(categoryDB);
        return mapper.toResponseDTO(product);
    }

    public ProductResponseDTO update(Long productId, ProductDTO productDTO){
        Product product = productRepository.findById(productId).orElseThrow(() ->
                new IllegalArgumentException("No se ha encontrado producto con ID " + productId));

        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());

        return mapper.toResponseDTO(productRepository.save(product));
    }


}
