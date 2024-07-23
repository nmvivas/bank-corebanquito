package com.banquito.core.bankdoc.dto;

import lombok.Builder;
import lombok.Value;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;

@Value
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class BankUserDTO {
    private String id;
    private String codeBank;
    private String uniqueId;
    private String nameBank;
    private String userName;
    private String lastName;
    private String fullName;
    private String typeUser;
    private String password;
    private LocalDateTime creationDate;
    private String state;
    private LocalDateTime lastLogin;
    private String email;
}
