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

    public void setId(String id) {
        this.id = id;
    }

    public void setCodeBank(String codeBank) {
        this.codeBank = codeBank;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public void setNameBank(String nameBank) {
        this.nameBank = nameBank;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setTypeUser(String typeUser) {
        this.typeUser = typeUser;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}