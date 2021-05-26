package com.chat.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.chat"})
@SpringBootApplication
public class UltimateAiApplication {

	public static void main(String[] args) {
		SpringApplication.run(UltimateAiApplication.class, args);
	}
}
