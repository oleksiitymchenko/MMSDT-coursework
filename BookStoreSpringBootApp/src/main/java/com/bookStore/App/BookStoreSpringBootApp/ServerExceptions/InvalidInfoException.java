package com.bookStore.App.BookStoreSpringBootApp.ServerExceptions;

public class InvalidInfoException extends RuntimeException {
    public InvalidInfoException() {
        super();
    }

    public InvalidInfoException(String message) {
        super(message);
    }
}
