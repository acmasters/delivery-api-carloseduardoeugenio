package com.deliverytech.delivery_api.service;

import com.deliverytech.delivery_api.dto.RestaurantDTO;
import com.deliverytech.delivery_api.entity.Restaurant;
import com.deliverytech.delivery_api.exception.ForbiddenException;
import com.deliverytech.delivery_api.repository.RestaurantRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    public RestaurantRepository repository;

    @Override
    public List<RestaurantDTO> getAllRestaurants() {
        return repository.findAll().stream().map(this::ConvertEntityToDTO)
                .collect(Collectors.toList());
    }

    public Long createRestaurant(RestaurantDTO restaurantDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Restaurant restaurant = modelMapper.map(restaurantDTO, Restaurant.class);
        Restaurant savedRestaurant = repository.save(restaurant);
        return savedRestaurant.getId();
    }

    @Override
    public RestaurantDTO updateRestaurant(Long id, RestaurantDTO restaurantDTO) {
        var restaurant = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Restaurant ID not found: %d".formatted(id)));
        if (restaurant.isActive()) {
            restaurant.setName(restaurantDTO.getName());
            restaurant.setName(restaurant.getDescription());
            repository.save(restaurant);
            return restaurantDTO;
        }
        throw new ForbiddenException("Restaurant is not active.");
    }

    private RestaurantDTO ConvertEntityToDTO(Restaurant entity){
        var restaurantDTO = new RestaurantDTO();
        restaurantDTO.setName(entity.getName());
        restaurantDTO.setDescription(entity.getDescription());
        return restaurantDTO;
    }
}