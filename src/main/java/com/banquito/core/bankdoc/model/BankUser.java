package com.banquito.core.bankdoc.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Document(collection = "bankUsers")
public class BankUser {

    @Id
    @NotNull
    private String id;
    
    @NotNull
    private String codeBank;

    @Indexed(unique = true)
    @NotNull
    private String uniqueId;

    @NotNull
    private String userName;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String fullName;

    @NotNull
    private String typeUser;

    @NotNull
    private String password;

    @NotNull
    private LocalDateTime creationDate;

    @NotNull
    private String state;

    @NotNull
    private LocalDateTime lastLogin;

    @NotNull
    private String email;
}
