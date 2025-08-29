package com.deliverytech.delivery_api.controller;

import com.deliverytech.delivery_api.dto.OrderDTO;
import com.deliverytech.delivery_api.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/order")
public class OrderController {
    @Autowired
    private OrderServiceImpl orderService;

    @GetMapping("/findAllOrders")
    public List<OrderDTO> getAllOrders() {
        return orderService.findAllOrders();
    }
}