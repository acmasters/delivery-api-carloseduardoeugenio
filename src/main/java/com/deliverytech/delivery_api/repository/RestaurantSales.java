package com.deliverytech.delivery_api.repository;

import java.math.BigDecimal;

public interface RestaurantSales {
    String getRestaurant();
    BigDecimal getTotalValue();
}