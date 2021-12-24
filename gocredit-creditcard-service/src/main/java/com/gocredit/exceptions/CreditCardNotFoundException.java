package com.gocredit.exceptions;

public class CreditCardNotFoundException extends RuntimeException {

    public CreditCardNotFoundException() {
    }

    public CreditCardNotFoundException(String message) {
        super(message);
    }
}
