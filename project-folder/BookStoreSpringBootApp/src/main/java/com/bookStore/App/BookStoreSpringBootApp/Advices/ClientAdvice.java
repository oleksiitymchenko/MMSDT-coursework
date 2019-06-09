package com.bookStore.App.BookStoreSpringBootApp.Advices;

import com.bookStore.App.BookStoreSpringBootApp.Exceptions.BadRequestException;
import com.bookStore.App.BookStoreSpringBootApp.Exceptions.ClientNotFoundException;
import com.bookStore.App.BookStoreSpringBootApp.Exceptions.ClientServerException;
import com.bookStore.App.BookStoreSpringBootApp.Exceptions.GenreNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ClientAdvice {

    @ResponseBody
    @ExceptionHandler(ClientNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String gameNotFoundHandler(GenreNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String gameServerHandler(BadRequestException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ClientServerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String gameServerHandler(ClientServerException ex) {
        return ex.getMessage();
    }
}
