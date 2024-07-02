package com.kardex.InventoryMicro.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1/consume-rest-api")
public class ClientController {

    @Autowired
    ConsumeApi consumeMockApiUtil;

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> consumeRestApi(@PathVariable("productId") String productId) {
        ProductDto productDto = consumeMockApiUtil.consumeMockApi(productId);
        return ResponseEntity.ok(productDto);
    }
}
