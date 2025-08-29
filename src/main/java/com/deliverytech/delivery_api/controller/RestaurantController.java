package com.deliverytech.delivery_api.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deliverytech.delivery_api.dto.RestaurantDTO;
import com.deliverytech.delivery_api.service.RestaurantService;

import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurantes")
@CrossOrigin(origins = "*")

@ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Restaurant created"),
        @ApiResponse(responseCode = "400", description = "Invalid data"),
        @ApiResponse(responseCode = "409", description = "Restaurant already exists"
        )})

public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    public List<RestaurantDTO> listAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    @PostMapping
    public ResponseEntity<Long> create(@Valid @RequestBody RestaurantDTO restaurantDTO) {
        Long ok = restaurantService.createRestaurant(restaurantDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(ok);
    }
    @PatchMapping("/{id}/restaurant")
    public ResponseEntity<RestaurantDTO> update(Long id, @RequestBody RestaurantDTO restaurantDTO) {
        RestaurantDTO savedRestaurant = restaurantService.updateRestaurant(id, restaurantDTO);
        return ResponseEntity.ok(savedRestaurant);
    }
}