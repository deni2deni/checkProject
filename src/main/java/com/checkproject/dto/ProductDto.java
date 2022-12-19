package com.checkproject.dto;

import lombok.Data;

@Data
public class ProductDto {

    private Long id;
    private String productName;
    private Float price;
    private Boolean discount;
    private Float discountRate = 0.0f;
    private Long quantity;
}
