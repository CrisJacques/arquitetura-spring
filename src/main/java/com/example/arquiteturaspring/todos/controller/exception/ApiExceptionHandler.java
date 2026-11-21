package com.example.arquiteturaspring.todos.controller.exception;

import com.example.arquiteturaspring.todos.service.exception.ObjectNotFoundException;
import com.example.arquiteturaspring.todos.service.exception.TodoAlreadyExistsException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ErrorResponse> objectNotFound(ObjectNotFoundException ex, HttpServletRequest request) {
        return buildErrorResponse(ex, request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TodoAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> todoAlreadyExists(TodoAlreadyExistsException ex, HttpServletRequest request) {
        return buildErrorResponse(ex, request, HttpStatus.UNPROCESSABLE_CONTENT);
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(
            RuntimeException ex,
            HttpServletRequest request,
            HttpStatus status) {

        var message = ex.getMessage();
        var dateTime = Instant.now();
        var path = request.getRequestURI();
        var err = new ErrorResponse(message, dateTime, path);

        return ResponseEntity.status(status).body(err);

    }
}
