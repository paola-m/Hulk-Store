package com.kardex.InventoryMicro.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductDto {

    @JsonProperty("idProduct")
    private Integer idProduct;

    @JsonProperty("name")
    private String name;

    @JsonProperty("categoryProduct")
    private String categoryProduct;

    @JsonProperty("description")
    private String description;

    @JsonProperty("created_at")
    private Date created_at;
}
