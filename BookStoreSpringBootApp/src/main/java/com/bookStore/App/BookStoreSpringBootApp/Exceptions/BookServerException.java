package com.bookStore.App.BookStoreSpringBootApp.Exceptions;

public class BookServerException extends RuntimeException {

    public BookServerException() {
        super("Book can not be created. Server error");
    }
}
