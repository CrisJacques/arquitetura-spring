package com.example.arquiteturaspring.todos.controller.exception;

import java.time.Instant;

public class ErrorResponse {

    private String message;
    private Instant dateTime;
    private String path;

    public ErrorResponse(String message, Instant dateTime, String path) {
        this.message = message;
        this.dateTime = dateTime;
        this.path = path;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Instant getDateTime() {
        return dateTime;
    }

    public void setDateTime(Instant dateTime) {
        this.dateTime = dateTime;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
