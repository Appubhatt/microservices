package com.easybank.card.exception;

public class CardAlreadyExistsException extends RuntimeException{

    public CardAlreadyExistsException(String message) {
        super(message);
    }
}
