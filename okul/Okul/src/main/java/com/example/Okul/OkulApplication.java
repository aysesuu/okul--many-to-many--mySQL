package com.example.Okul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.okul")
@ComponentScan(basePackages = "com.example.okul")
@Configuration
public class OkulApplication {

	public static void main(String[] args) {
		SpringApplication.run(OkulApplication.class, args);
	}

}
