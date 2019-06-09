package com.bookStore.App.BookStoreSpringBootApp.Exceptions;

public class ClientServerException extends RuntimeException {

    public ClientServerException() {
        super("Client can not be created. Server error");
    }
}
