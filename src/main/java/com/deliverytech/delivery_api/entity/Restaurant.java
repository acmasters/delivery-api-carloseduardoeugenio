package com.deliverytech.delivery_api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
 
    @Column(name = "name", nullable = false)
    private String name;    
    private String description;

    @OneToMany(mappedBy = "restaurant")
    private List <Product> products;

    @Enumerated(EnumType.STRING)
    @Setter
    private RestaurantStatus status;

    public boolean isActive() { return RestaurantStatus.ACTIVE.equals(getStatus());}
    public boolean isInactive() { return RestaurantStatus.INACTIVE.equals(getStatus());}
}