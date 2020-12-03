package com.sistema.sistemapontos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.sistema.controllers")
@EntityScan("com.sistema.models")
@EnableJpaRepositories("com.sistema.repository")

public class SistemapontosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemapontosApplication.class, args);
	}

}
