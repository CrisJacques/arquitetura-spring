package com.example.arquiteturaspring.todos.service.exception;

public class TodoAlreadyExistsException extends RuntimeException {
    public TodoAlreadyExistsException(String message) {
        super(message);
    }
}
