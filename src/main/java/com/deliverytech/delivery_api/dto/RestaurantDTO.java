package com.deliverytech.delivery_api.dto;
 
import com.deliverytech.delivery_api.entity.Restaurant;
import lombok.Data;
 
@Data
public class RestaurantDTO {
    private Long id;
    private String name;
    private String description;
}