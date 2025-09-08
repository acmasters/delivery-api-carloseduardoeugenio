package com.deliverytech.delivery_api.repository;

import com.deliverytech.delivery_api.entity.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findProductByCategory(String category);

    @Query(value = """
            SELECT * FROM product WHERE restaurant_id = '%param'
            """, nativeQuery = true)
    List<Product> findProductByRestaurantId(Long restaurantId);

    @Query(value = """
            select * from product where available=true;
            """, nativeQuery = true)
    List<Product> findByAvailableTrue();

    @Query(value = """
            select * from product wher price < '%param' limit 10; 
            """, nativeQuery = true)
    List<Product> findByPriceLessThanEqual(BigDecimal price);
}