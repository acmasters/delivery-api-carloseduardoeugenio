package com.deliverytech.delivery_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateStatusDTO {
    @NotBlank(message = "Email is mandatory!")
    @Email
    private String email;
    private boolean status;
}