package com.deliverytech.delivery_api.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrderDTO {
    private Long id;
    private LocalDateTime orderDate;
    private String deliveryAddress;
    private BigDecimal subtotal;
    private BigDecimal deliveryTax;
    private BigDecimal totalValue;

    public OrderDTO(){
        super();
    }

    public OrderDTO(
            Long id,
            LocalDateTime orderDate,
            String deliveryAddress,
            BigDecimal subtotal,
            BigDecimal deliveryTax,
            BigDecimal totalValue
    ) {
        this.id = id;
        this.orderDate = orderDate;
        this.deliveryAddress = deliveryAddress;
        this.subtotal = subtotal;
        this.deliveryTax = deliveryTax;
        this.totalValue = totalValue;
    }
}