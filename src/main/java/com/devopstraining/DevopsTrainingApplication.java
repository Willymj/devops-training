package com.devopstraining;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.devopstraining.backend.persistence.repositories")
public class DevopsTrainingApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevopsTrainingApplication.class, args);
	}
}
