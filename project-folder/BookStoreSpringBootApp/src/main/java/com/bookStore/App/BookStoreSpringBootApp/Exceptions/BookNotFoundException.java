package com.bookStore.App.BookStoreSpringBootApp.Exceptions;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(Integer id) {
        super("Book with id "+ id +" is not found");
    }
}
