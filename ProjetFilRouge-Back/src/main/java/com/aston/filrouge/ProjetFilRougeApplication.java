package com.aston.filrouge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = "com.aston.filrouge")
public class ProjetFilRougeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetFilRougeApplication.class, args);
	}
	
}
