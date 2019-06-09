package com.bookStore.App.BookStoreSpringBootApp.Advices;

import com.bookStore.App.BookStoreSpringBootApp.Exceptions.BadRequestException;
import com.bookStore.App.BookStoreSpringBootApp.Exceptions.BookNotFoundException;
import com.bookStore.App.BookStoreSpringBootApp.Exceptions.BookServerException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class BookAdvice {

    @ResponseBody
    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String gameNotFoundHandler(BookNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String gameServerHandler(BadRequestException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(BookServerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String gameServerHandler(BookServerException ex) {
        return ex.getMessage();
    }
}
