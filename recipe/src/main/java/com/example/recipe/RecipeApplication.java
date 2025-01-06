package com.example.recipe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:applicationContext.xml")
public class RecipeApplication {
	private static final Logger logger = LoggerFactory.getLogger(RecipeApplication.class);
	public static void main(String[] args) {
		logger.info("initShow() method invoked!");
		System.out.println("Main method is running...");
		SpringApplication.run(RecipeApplication.class, args);
	}

}
