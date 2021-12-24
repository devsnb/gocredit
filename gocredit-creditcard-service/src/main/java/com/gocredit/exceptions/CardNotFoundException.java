package com.gocredit.exceptions;

public class CardNotFoundException extends RuntimeException {

    public CardNotFoundException() {
    }

    /**
     *
     * @param message
     */
    public CardNotFoundException(String message) {
        super(message);
    }
}
