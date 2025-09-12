package com.deliverytech.delivery_api.controller;

import com.deliverytech.delivery_api.dto.CreateOrderItemDTO;
import com.deliverytech.delivery_api.dto.OrderDTO;
import com.deliverytech.delivery_api.dto.OrderItemDTO;
import com.deliverytech.delivery_api.dto.UpdateOrderDTO;
import com.deliverytech.delivery_api.dto.UpdateOrderStatusDTO;
import com.deliverytech.delivery_api.dto.request.OrderRequest;
import com.deliverytech.delivery_api.entity.Order;
import com.deliverytech.delivery_api.entity.OrderItem;
import com.deliverytech.delivery_api.entity.OrderStatus;
import com.deliverytech.delivery_api.entity.Product;
import com.deliverytech.delivery_api.repository.ClientRepository;
import com.deliverytech.delivery_api.repository.OrderRepository;
import com.deliverytech.delivery_api.repository.ProductRepository;
import com.deliverytech.delivery_api.repository.RestaurantRepository;
import com.deliverytech.delivery_api.repository.RestaurantSales;
import com.deliverytech.delivery_api.service.OrderServiceImpl;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/orders")
@RequiredArgsConstructor
@ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Order created"),
        @ApiResponse(responseCode = "400", description = "Invalid data"),
        @ApiResponse(responseCode = "409", description = "Order already exists"
        )})
public class OrderController {
    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/allOrders")
    public List<OrderDTO> getAllOrders() {
        return orderServiceImpl.findAllOrders();
    }

    @GetMapping("/status/{status}")
    public List<OrderDTO> getOrdersByStatus(@PathVariable OrderStatus status) {
        return orderRepository.findByOrderStatus(status).stream()
                .map(this::toDTO)
                .toList();
    }
    @GetMapping("/findbyOrderByClient")
    public ResponseEntity<List<OrderDTO>> findOrderByClient(@RequestParam("query") Long id) {
        List<OrderDTO> orderDTO = orderServiceImpl.findOrdersByClient(id);
        return ResponseEntity.ok(orderDTO);
    }
    @GetMapping("/client/{clientId}")
    public List<OrderDTO> getOrdersByClient(@PathVariable Long clientId) {
        return orderRepository.findByClientId(clientId).stream()
                .map(this::toDTO)
                .toList();
    }

    @GetMapping("/latest")
    public List<OrderDTO> getLatestOrders() {
        return orderRepository.findTop10ByOrderByOrderDateDesc()
                .stream()
                .map(this::toDTO)
                .toList();
    }
    @GetMapping("/ordersByDate")
    public List<OrderDTO> getOrdersBetweenDates(@RequestParam LocalDateTime start,
                                                @RequestParam LocalDateTime end) {
        return orderRepository.findByOrderDateBetween(start, end).stream()
                .map(this::toDTO)
                .toList();
    }

    @GetMapping("/sales")
    public List<RestaurantSales> getRestaurantSales() {
        return orderRepository.getTotalRestaurantSales();
    }

    @PostMapping("/create")
    public ResponseEntity<OrderDTO> createOrder(@Valid @RequestBody OrderRequest orderRequest) {
        OrderDTO orderDTO = orderServiceImpl.createOrder(orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderDTO);
    }

    @PatchMapping("/{id}/order")
    public ResponseEntity<OrderDTO> updateOrder(Long id, @RequestBody OrderStatus orderStatus) {
        OrderDTO savedOrder = orderServiceImpl.updateOrderStatus(id, orderStatus);
        return ResponseEntity.ok(savedOrder);
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<OrderDTO> cancelOrder(@PathVariable Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setOrderStatus(OrderStatus.CANCELLED);
        Order savedOrder = orderRepository.save(order);

        return ResponseEntity.ok(toDTO(savedOrder));
    }
    @PatchMapping("/{id}/status")
    public ResponseEntity<OrderDTO> updateOrderStatus(@PathVariable Long id,
                                                      @RequestBody UpdateOrderStatusDTO updateOrderStatusDTO) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setOrderStatus(updateOrderStatusDTO.orderStatus());
        Order saved = orderRepository.save(order);
        return ResponseEntity.ok(toDTO(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Long id, @RequestBody UpdateOrderDTO updateOrderDTO) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        if (order.getOrderStatus() != OrderStatus.PENDING) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }

        if (updateOrderDTO.deliveryAddress() != null && !updateOrderDTO.deliveryAddress().isBlank()) {
            order.setDeliveryAddress(updateOrderDTO.deliveryAddress());
        }

        if (updateOrderDTO.items() != null && !updateOrderDTO.items().isEmpty()) {
            order.getItems().clear();
            BigDecimal subtotal = BigDecimal.ZERO;

            for (CreateOrderItemDTO itemDTO : updateOrderDTO.items()) {
                Product product = productRepository.findById(itemDTO.productId())
                        .orElseThrow(() -> new EntityNotFoundException("Product not found"));

                OrderItem newItem = new OrderItem(order, product, itemDTO.quantity());
                order.getItems().add(newItem);

                subtotal = subtotal.add(newItem.getItemPrice()
                        .multiply(BigDecimal.valueOf(itemDTO.quantity())));
            }

            BigDecimal deliveryTax = order.getDeliveryTax() != null ? order.getDeliveryTax() : BigDecimal.valueOf(5.00);
            order.setSubtotal(subtotal);
            order.setTotalValue(subtotal.add(deliveryTax));
        }

        Order saved = orderRepository.save(order);

        return ResponseEntity.ok(toDTO(saved));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        if (!orderRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        orderRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private OrderDTO toDTO(Order order) {
        List<OrderItemDTO> items = order.getItems()
                .stream()
                .map(i -> new OrderItemDTO(
                        i.getProduct().getId(),
                        i.getProduct().getName(),
                        i.getQuantity(),
                        i.getItemPrice()
                )).toList();

        return new OrderDTO(
                order.getId(),
                order.getOrderDate(),
                order.getDeliveryAddress(),
                order.getSubtotal(),
                order.getDeliveryTax(),
                order.getTotalValue(),
                order.getOrderStatus(),
                items,
                order.getClient().getId(),
                order.getRestaurant().getId()
        );
    }
}