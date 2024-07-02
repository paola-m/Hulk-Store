package com.kardex.InventoryMicro.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;


import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDto {

    private Integer idInventory;
    private Integer productId;
    private Integer quantity;
    private Date updated_at;
}
