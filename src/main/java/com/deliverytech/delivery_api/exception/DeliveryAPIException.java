package com.deliverytech.delivery_api.exception;

public class DeliveryAPIException extends RuntimeException {

    public DeliveryAPIException(){
        super();
    }
    public DeliveryAPIException(String message) {
        super(message);
    }
}