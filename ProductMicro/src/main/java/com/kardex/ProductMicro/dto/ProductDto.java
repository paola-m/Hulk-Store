package com.kardex.ProductMicro.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductDto {

    private Integer idProduct;
    private String name;
    private String categoryProduct;
    private String description;
    private Date created_at;
}
