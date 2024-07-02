package com.kardex.ProductMicro.controller;

import com.kardex.ProductMicro.dto.ProductDto;
import com.kardex.ProductMicro.response.ResponseWrapper;
import com.kardex.ProductMicro.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/apiRest/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping(value="/{productId}")
    public ResponseEntity<ProductDto> getProductId (@Valid @PathVariable("productId") String transactionId) {
        return productService.getProductId(transactionId);
    }

    @PostMapping("/createProduct")
    public ResponseWrapper<ProductDto> createProduct(@Valid @RequestBody ProductDto request) {
        ResponseWrapper<ProductDto> productResponse = productService.createProduct(request);

         return new ResponseWrapper<>("Product created with success", productResponse.getData());

    }

    @PutMapping("/updateProduct")
    public ResponseWrapper<ProductDto> updateProduct(@Valid @RequestBody ProductDto updatedProductDto) {
        ResponseWrapper<ProductDto> productResponse = productService.updateProduct(updatedProductDto);
        return new ResponseWrapper<>("Product update with success", productResponse.getData());
    }
}
