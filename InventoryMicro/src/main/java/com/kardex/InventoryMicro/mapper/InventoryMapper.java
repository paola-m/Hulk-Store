package com.kardex.InventoryMicro.mapper;

import com.kardex.InventoryMicro.dto.InventoryDto;
import com.kardex.InventoryMicro.entity.InventoryEntity;

public interface InventoryMapper {

     InventoryDto toInventoryDto (InventoryEntity productEntity);
}