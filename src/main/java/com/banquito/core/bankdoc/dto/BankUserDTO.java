package com.banquito.core.bankdoc.dto;

import java.time.LocalDateTime;

import com.banquito.core.bankdoc.util.mapper.Default;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class BankUserDTO {
    @Default
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