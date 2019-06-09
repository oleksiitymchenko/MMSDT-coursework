package com.bookStore.App.BookStoreSpringBootApp.Exceptions;

public class GenreServerException extends RuntimeException {

    public GenreServerException() {
        super("Genre can not be created. Server error");
    }
}
