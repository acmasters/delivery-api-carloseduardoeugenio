package com.deliverytech.delivery_api.dto;

public record CreateOrderItemDTO(
        Long productId,
        int quantity
) { }
