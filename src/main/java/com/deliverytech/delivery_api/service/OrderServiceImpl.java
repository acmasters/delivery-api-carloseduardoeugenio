package com.deliverytech.delivery_api.service;

import com.deliverytech.delivery_api.dto.OrderDTO;
import com.deliverytech.delivery_api.dto.OrderItemDTO;
import com.deliverytech.delivery_api.entity.Order;
import com.deliverytech.delivery_api.entity.OrderStatus;
import com.deliverytech.delivery_api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository repository;

    public OrderServiceImpl(){
        super();
    }
    public OrderServiceImpl(OrderRepository repository) {
        this.repository = repository;
    }

//    public List<OrderDTO> findAllOrders() {
//        return repository.findAll()
//                .stream()
//                .map(this::ConvertEntityToDTO)
//                .collect(Collectors.toList());
//     }

    @Override
    public List<OrderDTO> findAllOrders() {
        return List.of();
    }

    @Transactional
    public Long createOrder(OrderDTO orderDTO) {
        return 0L;
    }

    @Override
    public OrderDTO findOrderById(Long id) {
        return null;
    }

    @Override
    public List<OrderDTO> findOrdersByClient(Long id) {
        return null;
    }

    @Override
    public OrderDTO updateOrderStatus(Long id, OrderStatus orderStatus) {
        return null;
    }

    @Override
    public OrderDTO deleteOrder(Long id) {
        return null;
    }

    @Override
    public OrderDTO calculateTotalOrder(List<OrderItemDTO> items) {
        return null;
    }

//    private OrderDTO ConvertEntityToDTO(Order entity) {
//            OrderDTO orderDTO = new OrderDTO();
//            orderDTO.setId(entity.getId());
//            orderDTO.setDeliveryAddress(entity.getDeliveryAddress());
//            orderDTO.setSubtotal(entity.getSubtotal());
//            orderDTO.setDeliveryTax(entity.getDeliveryTax());
//            orderDTO.setTotalValue(entity.getTotalValue());
//            return orderDTO;
//    }
}