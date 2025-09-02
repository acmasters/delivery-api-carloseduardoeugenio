package com.deliverytech.delivery_api.controller;

import com.deliverytech.delivery_api.dto.OrderDTO;
import com.deliverytech.delivery_api.dto.ProductDTO;
import com.deliverytech.delivery_api.entity.OrderStatus;
import com.deliverytech.delivery_api.service.OrderServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/findbyOrderByClient")
    public ResponseEntity<List<OrderDTO>> findOrderByClient(@RequestParam("query") Long id) {
        List<OrderDTO> orderDTO = orderService.findOrdersByClient(id);
        return ResponseEntity.ok(orderDTO);
    }

    @PostMapping
    public ResponseEntity<Long> createOrder(@Valid @RequestBody OrderDTO orderDTO) {
        Long ok = orderService.createOrder(orderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(ok);
    }

    @PatchMapping("/{id}/order")
    public ResponseEntity<OrderDTO> updateOrder(Long id, @RequestBody OrderStatus orderStatus) {
        OrderDTO savedOrder = orderService.updateOrderStatus(id, orderStatus);
        return ResponseEntity.ok(savedOrder);
    }
}