package com.deliverytech.delivery_api.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemsDTO {
    private Long id;
    private int quantity;
    private BigDecimal itemPrice;
    private BigDecimal subtotal;
}