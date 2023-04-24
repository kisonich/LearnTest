package com.kisonich.learntest.magaz.exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(long userId) {
        super("User not found with ID: " + userId);
    }
}