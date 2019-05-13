package com.labs.maven.springBoot.SpringBootMSC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SpringBootMscApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMscApplication.class, args);
	}
}
