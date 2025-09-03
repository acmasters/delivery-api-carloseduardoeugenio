package com.deliverytech.delivery_api.dto;

import com.deliverytech.delivery_api.entity.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderDTO(
        Long id,
        LocalDateTime orderDate,
        String deliveryAddress,
        BigDecimal subtotal,
        BigDecimal deliveryTax,
        BigDecimal totalValue,
        OrderStatus orderStatus,
        List<OrderItemDTO> items,
        Long clientId,
        Long restaurantId
) {}