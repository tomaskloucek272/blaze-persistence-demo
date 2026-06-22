package com.example.blaze_persistence_demo;

import com.blazebit.persistence.spring.data.repository.config.EnableBlazeRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBlazeRepositories
public class BlazePersistenceDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlazePersistenceDemoApplication.class, args);
	}

}
