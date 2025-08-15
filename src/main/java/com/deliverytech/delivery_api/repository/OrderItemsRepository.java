package com.deliverytech.delivery_api.repository;

import com.deliverytech.delivery_api.entity.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepository extends JpaRepository<OrderItems, Long> {
}