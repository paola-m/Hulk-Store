package com.kardex.ProductMicro.mapper;

import com.kardex.ProductMicro.dto.ProductDto;
import com.kardex.ProductMicro.entity.ProductEntity;

public class ProductMapperServiceImp implements ProductMapper{
    @Override
    public ProductDto toProductDto(ProductEntity productEntity) {
        ProductDto dto = new ProductDto();

        dto.setIdProduct(productEntity.getIdProduct());
        dto.setName(productEntity.getName());
        dto.setCategoryProduct(productEntity.getCategoryProduct());
        dto.setDescription(productEntity.getDescription());
        dto.setCreated_at(productEntity.getCreated_at());
        return dto;
    }
}
