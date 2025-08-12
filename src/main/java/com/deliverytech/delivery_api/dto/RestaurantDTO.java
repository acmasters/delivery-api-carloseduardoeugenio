package com.deliverytech.delivery_api.dto;
 
import lombok.Data;
 
@Data
public class RestaurantDTO {
    private Long id;
    private String name;
    private String description;  
   
    public RestaurantDTO() {
        super();
    }
 
    @Override
    public String toString() {
        return "RestaurantDTO{" +
               "name='" + name + '\'' +
               ", description='" + description + '\'' +
               '}';
    }  
 
    public RestaurantDTO(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;      
    }            
}