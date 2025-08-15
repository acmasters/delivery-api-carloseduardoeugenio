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

    public ClientDTO() {
        super();
    }

    public ClientDTO(
            Long id,
            String name,
            String email,
            String phone,
            String address,
            boolean active
    ) {
        this.id = id;
        this.name =  name;
        this.email =  email;
        this.phone = phone;
        this.address = address;
        this.active = active;
    }
}