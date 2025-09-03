package com.deliverytech.delivery_api.repository;

import com.deliverytech.delivery_api.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepository extends JpaRepository<OrderItem, Long> {
}