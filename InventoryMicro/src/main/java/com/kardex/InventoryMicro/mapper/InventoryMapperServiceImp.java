package com.kardex.InventoryMicro.mapper;


import com.kardex.InventoryMicro.dto.InventoryDto;
import com.kardex.InventoryMicro.entity.InventoryEntity;
import org.springframework.stereotype.Component;

@Component
public class InventoryMapperServiceImp implements InventoryMapper {

    @Override
    public InventoryDto toInventoryDto(InventoryEntity inventoryEntity) {
        InventoryDto dto = new InventoryDto();

        dto.setIdInventory(inventoryEntity.getId());
        dto.setProductId(inventoryEntity.getProductId());
        dto.setQuantity(inventoryEntity.getQuantity());
        dto.setUpdated_at(inventoryEntity.getUpdated_at());

        return dto;
    }


}
