package com.banquito.core.bankdoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.banquito.core.bankdoc")
public class BankdocApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankdocApplication.class, args);
    }
}
