package com.yummiqr.restaurant_service.mapper;

import com.yummiqr.restaurant_service.dto.request.ProductDTO;
import com.yummiqr.restaurant_service.dto.response.ProductResponseDTO;
import com.yummiqr.restaurant_service.model.Product;
import org.mapstruct.Mapper;

import java.util.List;

public class ProductMapper {

    public Product toEntity(ProductDTO productDTO){
        return null;
    }
    public ProductResponseDTO toResponseDTO(Product product){
        return null;
    }
    public List<ProductResponseDTO> toResponseDTOList(List<Product> product){
        return null;
    }
}
