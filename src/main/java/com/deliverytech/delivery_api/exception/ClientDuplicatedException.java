package com.deliverytech.delivery_api.exception;

public class ClientDuplicatedException extends Exception {
    public ClientDuplicatedException(){
        super();
    }
    public ClientDuplicatedException(String message) {
        super(message);
    }
}
