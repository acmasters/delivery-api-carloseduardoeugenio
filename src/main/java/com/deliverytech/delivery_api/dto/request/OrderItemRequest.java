package com.deliverytech.delivery_api.dto.request;

public record OrderItemRequest(
        Long productId,
        int quantity
) {}