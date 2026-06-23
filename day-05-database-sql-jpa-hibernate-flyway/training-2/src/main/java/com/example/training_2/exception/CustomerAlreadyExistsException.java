package com.example.training_2.exception;

public class CustomerAlreadyExistsException extends RuntimeException {
    public CustomerAlreadyExistsException(String message) {
        super(message);
    }
}