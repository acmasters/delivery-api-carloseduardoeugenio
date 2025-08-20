package com.deliverytech.delivery_api.service;

import com.deliverytech.delivery_api.dto.OrderDTO;
import com.deliverytech.delivery_api.entity.Order;
import com.deliverytech.delivery_api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepository repository;

    private OrderService(){
        super();
    }
    private OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public List<OrderDTO> findAllOrders() {
        return repository.findAll()
                .stream()
                .map(this::ConvertEntityToDTO)
                .collect(Collectors.toList());
     }

        private OrderDTO ConvertEntityToDTO(Order entity) {
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setId(entity.getId());
            orderDTO.setDeliveryAddress(entity.getDeliveryAddress());
            orderDTO.setSubtotal(entity.getSubtotal());
            orderDTO.setDeliveryTax(entity.getDeliveryTax());
            orderDTO.setTotalValue(entity.getTotalValue());
            return orderDTO;
    }
}