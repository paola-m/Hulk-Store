package com.kardex.ProductMicro.mapper;

import com.kardex.ProductMicro.dto.ProductDto;
import com.kardex.ProductMicro.entity.ProductEntity;
import org.mapstruct.Mapper;


public interface ProductMapper {

     ProductDto toProductDto (ProductEntity productEntity);
}