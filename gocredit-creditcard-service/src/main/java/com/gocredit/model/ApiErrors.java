package com.gocredit.model;

import org.springframework.http.HttpStatus;

import java.time.LocalDate;

public class ApiErrors {
    LocalDate timestamp;
    HttpStatus status;
    String message;
    String path;

    public ApiErrors() {
    }

    public ApiErrors(LocalDate timestamp, HttpStatus status, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.path = path;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "ApiErrors{" +
                "timestamp=" + timestamp +
                ", status=" + status +
                ", message='" + message + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
