package com.kardex.ProductMicro.service;

import com.kardex.ProductMicro.dto.ProductDto;
import com.kardex.ProductMicro.response.ResponseWrapper;
import org.springframework.http.ResponseEntity;

public interface ProductService {

    ResponseEntity<ProductDto> getProductId(String id);
    ResponseWrapper<ProductDto> createProduct(ProductDto product);
    ResponseWrapper<ProductDto> updateProduct(ProductDto product);

}
