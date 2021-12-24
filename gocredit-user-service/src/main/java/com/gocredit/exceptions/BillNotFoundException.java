package com.gocredit.exceptions;

public class BillNotFoundException extends RuntimeException {
    public BillNotFoundException() {
    }

    public BillNotFoundException(String message) {
        super(message);
    }
}
