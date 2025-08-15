package com.deliverytech.delivery_api.repository;

import com.deliverytech.delivery_api.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}