package com.deliverytech.delivery_api.repository;

import com.deliverytech.delivery_api.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findProductByCategory(String category);

}