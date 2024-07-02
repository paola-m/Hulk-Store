package com.kardex.InventoryMicro.dto.errors;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Builder
@Getter
@Setter
public class ErrorDetailDto implements Serializable{

    private static final Long ID = 1L;
    private String code;
    private String description;
    private String tecError;
}
