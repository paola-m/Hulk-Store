package com.kardex.InventoryMicro.service;

import com.kardex.InventoryMicro.dto.InventoryDto;
import com.kardex.InventoryMicro.response.ResponseWrapper;
import org.springframework.http.ResponseEntity;

public interface InventoryService {

    ResponseEntity<InventoryDto> getInventoryId(String id);
    ResponseWrapper<InventoryDto> createInventory(InventoryDto product);
    ResponseWrapper<InventoryDto> updateInventory(InventoryDto product);

}
