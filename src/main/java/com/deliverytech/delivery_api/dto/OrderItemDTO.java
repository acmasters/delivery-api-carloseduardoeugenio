package com.deliverytech.delivery_api.dto;

import java.math.BigDecimal;

public record OrderItemDTO(
    Long productId,
    String productName,
    int quantity,
    BigDecimal price
    ) {}