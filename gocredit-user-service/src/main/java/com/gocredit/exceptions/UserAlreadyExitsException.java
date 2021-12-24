package com.gocredit.exceptions;

public class UserAlreadyExitsException extends RuntimeException {
    public UserAlreadyExitsException() {
    }

    public UserAlreadyExitsException(String message) {
        super(message);
    }
}
