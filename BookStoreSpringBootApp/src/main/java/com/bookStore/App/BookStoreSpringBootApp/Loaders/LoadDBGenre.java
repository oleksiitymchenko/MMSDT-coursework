package com.bookStore.App.BookStoreSpringBootApp.Loaders;

import com.bookStore.App.BookStoreSpringBootApp.DAL.GenreRepository;
import com.bookStore.App.BookStoreSpringBootApp.Models.Genre;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class LoadDBGenre {
    @Bean
    public CommandLineRunner LoadDBGenre(GenreRepository repository){
        return args ->{
                repository.save((new Genre("Fantasy")));
                repository.save(new Genre("Science"));
                repository.save(new Genre("TEst1"));
                repository.save(new Genre("Test2"));
        };
    }
}
