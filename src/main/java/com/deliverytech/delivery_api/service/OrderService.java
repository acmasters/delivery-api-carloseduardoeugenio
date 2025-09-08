package com.deliverytech.delivery_api.service;

import com.deliverytech.delivery_api.dto.OrderDTO;
import com.deliverytech.delivery_api.dto.OrderItemDTO;
import com.deliverytech.delivery_api.dto.request.OrderRequest;
import com.deliverytech.delivery_api.entity.OrderStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {
    List<OrderDTO> findAllOrders();
    OrderDTO createOrder(OrderRequest orderRequest);
    OrderDTO findOrderById(Long id);
    List<OrderDTO> findOrdersByClient(Long id);
    OrderDTO updateOrderStatus(Long id, OrderStatus orderStatus);
    ResponseEntity<OrderDTO> deleteOrder(Long id);
    OrderDTO calculateTotalOrder(List<OrderItemDTO> items);
    List<OrderDTO> findByOrderStatus(OrderStatus orderStatus);
}