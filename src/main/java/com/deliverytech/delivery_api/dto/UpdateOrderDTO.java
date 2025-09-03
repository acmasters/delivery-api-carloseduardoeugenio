package com.deliverytech.delivery_api.dto;

import java.util.List;

public record UpdateOrderDTO(
        String deliveryAddress,
        List<CreateOrderItemDTO> items
) { }
