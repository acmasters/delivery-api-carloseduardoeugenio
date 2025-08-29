package com.deliverytech.delivery_api.repository;

import com.deliverytech.delivery_api.entity.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepositoryCustom {
    public List<Product> findProductByRestaurantId(Long restauranteId);

    public List<Product> findByAvailabilityIsTrue();

    public List<Product> findByCategory(String category);

    public List<Product> findByPriceLessThanEqual(BigDecimal price);
}