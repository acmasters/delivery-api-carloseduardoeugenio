package com.deliverytech.delivery_api.repository;

import com.deliverytech.delivery_api.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Optional<Restaurant> findRestaurantByName(String name);

    @Query(value = """
            SELECT * FROM product WHERE category = '%param'
            """, nativeQuery = true)
    List<Restaurant> findProductByCategory(String category);

    @Query(value = """

            """, nativeQuery = true)
    List<Restaurant>findByStatusTrue();

    @Query(value = """

            """, nativeQuery = true)
    List<Restaurant>findByCuisine(String cuisine);

    @Query(value = """

            """, nativeQuery = true)
    List<Restaurant>findTop5ByOrderByNameAsc();
}