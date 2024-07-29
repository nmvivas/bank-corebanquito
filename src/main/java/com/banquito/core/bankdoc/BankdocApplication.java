package com.banquito.core.bankdoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.banquito.core.bankdoc.repository")
public class BankdocApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankdocApplication.class, args);
	}
}
