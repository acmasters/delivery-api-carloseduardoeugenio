package com.deliverytech.delivery_api.dto;

import java.util.List;

public record CreateOrderDTO(
        String deliveryAddress,
        Long clientId,
        Long restaurantId,
        List<CreateOrderItemDTO> items
) { }
