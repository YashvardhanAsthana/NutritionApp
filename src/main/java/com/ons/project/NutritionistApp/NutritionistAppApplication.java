package com.ons.project.NutritionistApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins = "http://localhost:3000")
public class NutritionistAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(NutritionistAppApplication.class, args);
	}

}
