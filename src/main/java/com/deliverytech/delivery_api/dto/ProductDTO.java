package com.deliverytech.delivery_api.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String category;
    private boolean isAvailable;

    public ProductDTO(){
        super();
    }

    public ProductDTO (String name, String description, BigDecimal price,
                       String category, boolean isAvailable) {
        this.name = name;
        this.description =  description;
        this.price = price;
        this.category = category;
        this.isAvailable = isAvailable;
    }
}