package com.deliverytech.delivery_api.repository;

import com.deliverytech.delivery_api.entity.Order;
import com.deliverytech.delivery_api.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByClientId(Long clientId);

    List<Order> findByOrderStatus(OrderStatus orderStatus);

    List<Order> findTop10ByOrderByOrderDateDesc();

    List<Order> findByOrderDateBetween(LocalDateTime start, LocalDateTime end);

    @Query(value = """
        SELECT r.name as restaurant, SUM(o.total_value) as totalValue
        FROM orders o
        JOIN restaurant r ON r.id = o.restaurant_id
        GROUP BY r.name
    """, nativeQuery = true)
    List<RestaurantSales> getTotalRestaurantSales();
}