package com.bookStore.App.BookStoreSpringBootApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BookStoreSpringBootAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookStoreSpringBootAppApplication.class, args);
	}

}
