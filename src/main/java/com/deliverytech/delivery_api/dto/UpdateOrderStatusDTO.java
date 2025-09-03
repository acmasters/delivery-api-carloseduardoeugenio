package com.deliverytech.delivery_api.dto;

import com.deliverytech.delivery_api.entity.OrderStatus;

public record UpdateOrderStatusDTO(
        OrderStatus orderStatus
) {}
