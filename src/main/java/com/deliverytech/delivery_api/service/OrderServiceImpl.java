package com.deliverytech.delivery_api.service;

import com.deliverytech.delivery_api.dto.CreateOrderItemDTO;
import com.deliverytech.delivery_api.dto.OrderDTO;
import com.deliverytech.delivery_api.dto.OrderItemDTO;
import com.deliverytech.delivery_api.dto.UpdateOrderDTO;
import com.deliverytech.delivery_api.dto.UpdateOrderStatusDTO;
import com.deliverytech.delivery_api.dto.request.OrderItemRequest;
import com.deliverytech.delivery_api.dto.request.OrderRequest;
import com.deliverytech.delivery_api.entity.Client;
import com.deliverytech.delivery_api.entity.Order;
import com.deliverytech.delivery_api.entity.OrderItem;
import com.deliverytech.delivery_api.entity.OrderStatus;
import com.deliverytech.delivery_api.entity.Product;
import com.deliverytech.delivery_api.entity.Restaurant;
import com.deliverytech.delivery_api.repository.ClientRepository;
import com.deliverytech.delivery_api.repository.OrderRepository;
import com.deliverytech.delivery_api.repository.ProductRepository;
import com.deliverytech.delivery_api.repository.RestaurantRepository;
import com.deliverytech.delivery_api.repository.RestaurantSales;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    public OrderServiceImpl(){
        super();
    }

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<OrderDTO> findAllOrders() {
        return List.of();
    }

    @Transactional
    public OrderDTO createOrder(OrderRequest orderRequest) {
        var clientId = orderRequest.clientId();
        Client client =  clientRepository.findById(clientId)
                .orElseThrow(() -> new EntityNotFoundException("Client ID not found: %d".formatted(clientId)));
        var restaurantId = orderRequest.restaurantId();
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant ID not found: %d".formatted(restaurantId)));
    Order order = new Order();
    order.setOrderDate(LocalDateTime.now());
    order.setDeliveryAddress(orderRequest.deliveryAddress());
    order.setClient(client);
    order.setRestaurant(restaurant);
    order.setOrderStatus(OrderStatus.CREATED);
    BigDecimal subtotal =  BigDecimal.ZERO;

    for (OrderItemRequest itemRequest : orderRequest.items()) {
        var productId = itemRequest.productId();
        Product product =  productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product ID not found: %d".formatted(productId)));
        OrderItem orderItem =  new OrderItem(order, product, itemRequest.quantity());
        order.getItems().add(orderItem);

        subtotal = subtotal.add(product.getPrice().multiply(BigDecimal.valueOf(itemRequest.quantity())));
    }
    order.setSubtotal(subtotal);
    order.setDeliveryTax(BigDecimal.valueOf(5.00));
    order.setTotalValue(subtotal.add(order.getDeliveryTax()));

    Order savedOrder = orderRepository.save(order);
    return toDTO(savedOrder);
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
    public OrderDTO calculateTotalOrder(List<OrderItemDTO> items) {
        return null;
    }

    public ResponseEntity<OrderDTO> updateOrder(Long id, UpdateOrderDTO updateOrderDTO) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (order.getOrderStatus() != OrderStatus.PENDING) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }

        if (updateOrderDTO.deliveryAddress() != null &&
                !updateOrderDTO.deliveryAddress().isBlank()) {
            order.setDeliveryAddress(updateOrderDTO.deliveryAddress());
        }

        if (updateOrderDTO.items() != null && !updateOrderDTO.items().isEmpty()) {
            order.getItems().clear();
            BigDecimal subtotal = BigDecimal.ZERO;

            for (CreateOrderItemDTO itemDTO : updateOrderDTO.items()) {
                Product product = productRepository.findById(itemDTO.productId())
                        .orElseThrow(() -> new RuntimeException("Product not found"));

                OrderItem newItem = new OrderItem(order, product, itemDTO.quantity());
                order.getItems().add(newItem);

                subtotal = subtotal.add(newItem.getItemPrice().multiply(BigDecimal.valueOf(itemDTO.quantity())));
            }

            BigDecimal deliveryTax = order.getDeliveryTax() != null ? order.getDeliveryTax() : BigDecimal.valueOf(5.00);
            order.setSubtotal(subtotal);
            order.setTotalValue(subtotal.add(deliveryTax));
        }
        Order saved = orderRepository.save(order);
        return ResponseEntity.ok(toDTO(saved));
    }

    public List<OrderDTO> findByOrderStatus(OrderStatus orderStatus) {
        return orderRepository.findByOrderStatus(orderStatus).stream().map(this::toDTO).toList();
    }
    public List<OrderDTO> findByClientId(Long id) {
        return orderRepository.findByClientId(id).stream().map(this::toDTO).toList();
    }
    public List<OrderDTO> findTop10ByOrderByOrderDateDesc() {
        return orderRepository.findTop10ByOrderByOrderDateDesc().stream().map(this::toDTO).toList();
    }
    public List<OrderDTO> getOrdersBetweenDates(LocalDateTime start, LocalDateTime end) {
        return orderRepository.findByOrderDateBetween(start, end).stream().map(this::toDTO).toList();
    }
    public List<RestaurantSales> getRestaurantSales() {
        return orderRepository.getTotalRestaurantSales();
    }

    public ResponseEntity<OrderDTO> cancelOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order not found"));
        order.setOrderStatus(OrderStatus.CANCELLED);
        Order saved = orderRepository.save(order);
        return ResponseEntity.ok(toDTO(saved));
    }

    public ResponseEntity<OrderDTO> updateOrderStatus(Long id, UpdateOrderStatusDTO updateOrderStatusDTO) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order not found"));
        order.setOrderStatus(updateOrderStatusDTO.orderStatus());
        Order saved = orderRepository.save(order);
        return ResponseEntity.ok(toDTO(saved));
    }

    public ResponseEntity<OrderDTO> deleteOrder(Long id) {
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