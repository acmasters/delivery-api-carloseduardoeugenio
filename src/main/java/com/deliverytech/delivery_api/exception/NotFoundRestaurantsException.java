package com.deliverytech.delivery_api.exception;

public class NotFoundRestaurantsException extends NotFoundException {
    public NotFoundRestaurantsException() {
        super("Not found restaurants");
    }

}
