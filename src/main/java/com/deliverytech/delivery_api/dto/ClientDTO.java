package com.deliverytech.delivery_api.dto;

import lombok.Data;

@Data
public class ClientDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private boolean active;
}