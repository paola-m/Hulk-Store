package com.kardex.InventoryMicro.controller;

import com.kardex.InventoryMicro.dto.InventoryDto;
import com.kardex.InventoryMicro.response.ResponseWrapper;
import com.kardex.InventoryMicro.service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(path = "/apiRest/inventory")
public class InventoryController {

    private final RestTemplate restTemplate;

    @Autowired
    InventoryService inventoryService;

    public InventoryController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping(value="/{inventoryId}")
    public ResponseEntity<InventoryDto> getInventoryId (@Valid @PathVariable("inventoryId") String transactionId) {
        return inventoryService.getInventoryId(transactionId);
    }

    @PostMapping("/createInventory")
    public ResponseWrapper<InventoryDto> createProduct(@Valid @RequestBody InventoryDto request) {
        ResponseWrapper<InventoryDto> inventoryResponse = inventoryService.createInventory(request);

         return new ResponseWrapper<>("Inventory created with success", inventoryResponse.getData());

    }

    @PutMapping("/updateInventory")
    public ResponseWrapper<InventoryDto> updateProduct(@Valid @RequestBody InventoryDto updatedInventoryDto) {
        ResponseWrapper<InventoryDto> productResponse = inventoryService.updateInventory(updatedInventoryDto);
        return new ResponseWrapper<>("Product update with success", productResponse.getData());
    }

    @GetMapping(value="/productConsu/{inventoryId}")
    public Object getProductId (@Valid @PathVariable("inventoryId") String transactionId) {
         String url = "http://localhost:8080/apiRest/product/01";
        return restTemplate.getForObject(url, Object.class);
    }

}
