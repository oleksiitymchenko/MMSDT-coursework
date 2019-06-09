package com.bookStore.App.BookStoreSpringBootApp.Exceptions;

public class GenreNotFoundException extends RuntimeException {

    public GenreNotFoundException(Integer id) {
        super("Genre with id "+ id +" is not found");
    }
}
