package com.deliverytech.delivery_api.dto.request;

import java.util.List;

public record OrderRequest(
        String deliveryAddress,
        List<OrderItemRequest> items,
        Long clientId,
        Long restaurantId
) {}