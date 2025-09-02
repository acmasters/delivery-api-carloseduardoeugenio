package com.deliverytech.delivery_api.service;

import com.deliverytech.delivery_api.dto.OrderDTO;
import com.deliverytech.delivery_api.dto.OrderItemsDTO;
import com.deliverytech.delivery_api.entity.OrderStatus;

import java.util.List;

public interface OrderService {
    List<OrderDTO> findAllOrders();
    Long createOrder(OrderDTO orderDTO);
    OrderDTO findOrderById(Long id);
    List<OrderDTO> findOrdersByClient(Long id);
    OrderDTO updateOrderStatus(Long id, OrderStatus orderStatus);
    OrderDTO deleteOrder(Long id);
    OrderDTO calculateTotalOrder(List<OrderItemsDTO> items);
}