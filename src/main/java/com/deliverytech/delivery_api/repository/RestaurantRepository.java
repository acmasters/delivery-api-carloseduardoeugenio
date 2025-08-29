package com.deliverytech.delivery_api.repository;

import com.deliverytech.delivery_api.entity.Product;
import com.deliverytech.delivery_api.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
//    @Query(value = """
//            SELECT * FROM product WHERE category = '%param'
//            """, nativeQuery = true)
//    List<Restaurant> findByCategory(String category);
//
//    @Query(value = """
//
//            """, nativeQuery = true)
//    List<Restaurant>findByActiveRestaurantTrue();

//    @Query(value = """
//
//            """, nativeQuery = true)
//    List<Restaurant>findByDeliveryTaxLessThanEqual(BigDecimal tax);

//    @Query(value = """
//
//            """, nativeQuery = true)
//    List<Restaurant>findTop5ByOrderByNomeAsc();
}