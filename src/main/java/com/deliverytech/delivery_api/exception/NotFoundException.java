package com.deliverytech.delivery_api.exception;

public class NotFoundException extends DeliveryAPIException {
    
    public NotFoundException() {
        super();
    }
    public NotFoundException(String message) {
        super(message);
    }
}