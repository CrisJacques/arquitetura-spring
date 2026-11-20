package com.example.arquiteturaspring.todos.controller.exception;

import com.example.arquiteturaspring.todos.service.exception.ObjectNotFoundException;
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
        var message = ex.getMessage();
        var dateTime = Instant.now();
        var path = request.getRequestURI();
        var err = new ErrorResponse(message, dateTime, path);

        HttpStatus status = HttpStatus.NOT_FOUND;

        return ResponseEntity.status(status).body(err);
    }
}
