package com.academic.studytime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching // chache do Spring
public class StudytimeApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudytimeApplication.class, args);
	}

}
