package com.bookStore.App.BookStoreSpringBootApp.Loaders;

import com.bookStore.App.BookStoreSpringBootApp.DAL.ClientRepository;
import com.bookStore.App.BookStoreSpringBootApp.Models.Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class LoadDBCustomer {
    @Bean
    public CommandLineRunner LoadDBCustomer(ClientRepository repository){
        return args ->{
            repository.save((new Client("John","Smith","john@smith")));
            repository.save(new Client("Ivan","Black","johnny@black"));
        };



































    }
}
