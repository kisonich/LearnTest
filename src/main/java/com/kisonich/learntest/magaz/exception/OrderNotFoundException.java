package com.kisonich.learntest.magaz.exception;

public class OrderNotFoundException extends Exception {
    public OrderNotFoundException(long id) {
        super("Order not found with ID: " + id);

    }
}
