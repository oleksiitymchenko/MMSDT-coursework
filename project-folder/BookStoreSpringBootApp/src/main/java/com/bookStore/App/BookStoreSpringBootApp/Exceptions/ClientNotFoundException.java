package com.bookStore.App.BookStoreSpringBootApp.Exceptions;

public class ClientNotFoundException extends RuntimeException {

    public ClientNotFoundException(Integer id) {
        super("Client with id "+ id +" is not found");
    }
}
