package com.bookStore.App.BookStoreSpringBootApp.Exceptions;

public class BadRequestException extends RuntimeException {

    public BadRequestException() {
        super("Check your input data one more time");
    }
}
