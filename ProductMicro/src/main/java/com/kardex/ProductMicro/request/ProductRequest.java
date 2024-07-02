package com.kardex.ProductMicro.request;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductRequest {

    private String name;
    private String categoryProduct;
    private String description;
}
