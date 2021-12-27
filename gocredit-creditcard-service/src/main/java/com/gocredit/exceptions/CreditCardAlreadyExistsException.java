package com.gocredit.exceptions;

public class CreditCardAlreadyExistsException extends RuntimeException {
    public CreditCardAlreadyExistsException() {
    }

    public CreditCardAlreadyExistsException(String message) {
        super(message);
    }
}
