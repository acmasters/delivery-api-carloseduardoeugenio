package com.deliverytech.delivery_api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
@NotNull(message = "Product ID is invalid value is null")
@Positive(message = "Product ID is not positive")
public class OrderItemsDTO {
    private Long id;
    @NotNull @Min(1)
    private int quantity;
    private BigDecimal itemPrice;
    private BigDecimal subtotal;
}