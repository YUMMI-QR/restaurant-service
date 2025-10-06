package com.yummiqr.restaurant_service.mapper;

import com.yummiqr.restaurant_service.dto.request.ProductDTO;
import com.yummiqr.restaurant_service.dto.response.ProductResponseDTO;
import com.yummiqr.restaurant_service.model.Product;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    private final CategoryMapper categoryMapper;

    public Product toEntity(ProductDTO productDTO){
        return productDTO == null ? null :
                Product.builder()
                        .id(productDTO.getId())
                        .name(productDTO.getName())
                        .price(productDTO.getPrice())
                        .description(productDTO.getDescription())
                        .category(productDTO.getCategory())
                        .build();
    }
    public ProductResponseDTO toResponseDTO(Product product){
        return product == null ? null :
                ProductResponseDTO.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .price(product.getPrice())
                        .description(product.getDescription())
                        .category(categoryMapper.toResponseDTO(product.getCategory()))
                        .build();
    }
    public List<ProductResponseDTO> toResponseDTOList(List<Product> productList){
        return productList == null ? null : productList.stream().map(this::toResponseDTO).collect(Collectors.toList());
    }
}
