package com.bookStore.App.BookStoreSpringBootApp.Loaders;

import com.bookStore.App.BookStoreSpringBootApp.DAL.BookRepository;
import com.bookStore.App.BookStoreSpringBootApp.Models.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class LoadDBGame {
    @Bean
    public CommandLineRunner LoadDBGame(BookRepository repository){
        return args ->{
                repository.save((new Book("Amazing adventures",1999)));
                repository.save(new Book("CLR via C#",2014));
        };
    }
}
