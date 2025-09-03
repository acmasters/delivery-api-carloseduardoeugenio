package com.deliverytech.delivery_api.service;
import com.deliverytech.delivery_api.dto.RestaurantDTO;

import java.util.List;

public interface RestaurantService {
    List<RestaurantDTO> getAllRestaurants();
    Long createRestaurant(RestaurantDTO restaurantDTO);
    RestaurantDTO updateRestaurant(Long id, RestaurantDTO restaurantDTO);
    RestaurantDTO findbyRestaurantName(String name);
    void deleteRestaurant(Long id);
}