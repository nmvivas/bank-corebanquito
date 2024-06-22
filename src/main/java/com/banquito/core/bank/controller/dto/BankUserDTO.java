package com.banquito.core.bank.controller.dto;

import lombok.Builder;
import lombok.Value;
import java.time.LocalDateTime;

@Value
@Builder
public class BankUserDTO {

    private Long id;
    private String codeRole;
    private String nameRole;
    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    private String state;
    private LocalDateTime lastLogin;
    private String email;

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }
}
