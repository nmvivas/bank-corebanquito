package com.banquito.core.bank.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class BankUserDTO {

    private Long id;
    private String codeRole;
    private String nameRole;
    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    private String state;
    private LocalDate lastLogin;
    private String email;

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }
}
