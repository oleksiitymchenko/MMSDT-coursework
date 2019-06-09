package com.bookStore.App.BookStoreSpringBootApp.Advices;

import com.bookStore.App.BookStoreSpringBootApp.Exceptions.BadRequestException;
import com.bookStore.App.BookStoreSpringBootApp.Exceptions.GenreNotFoundException;
import com.bookStore.App.BookStoreSpringBootApp.Exceptions.GenreServerException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GenreAdvice {

    @ResponseBody
    @ExceptionHandler(GenreNotFoundException.class)
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
    @ExceptionHandler(GenreServerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String gameServerHandler(GenreServerException ex) {
        return ex.getMessage();
    }
}
