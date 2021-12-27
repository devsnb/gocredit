package com.gocredit.exceptions;

public class InvalidUserCredentials extends RuntimeException{

    public InvalidUserCredentials() {
    }

    public InvalidUserCredentials(String message) {
        super(message);
    }
}
