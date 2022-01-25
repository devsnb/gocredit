package com.gocredit.exceptions;

public class PaymentNotFoundException extends RuntimeException {
    public PaymentNotFoundException() {
    }

    public PaymentNotFoundException(String message) {
        super(message);
    }
}
