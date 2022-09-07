package com.coding.interview.springboot;

import com.coding.interview.springboot.model.JokeResponse;
import com.coding.interview.springboot.service.JokerClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		JokerClient jokerClient = context.getBean(JokerClient.class);
		JokeResponse response = jokerClient.getJokerResponse().block();
		System.out.println(">> message = " + response.getSetup() + "%n" + response.getDelivery());

	}



}
