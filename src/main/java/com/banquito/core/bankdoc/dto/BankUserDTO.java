package com.banquito.core.bankdoc.dto;

import lombok.Builder;
import lombok.Value;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Value
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class BankUserDTO {
    private String id;
    private String username;
    private String password;
    private String role;
    private String bankCode;
    private String startDate;
}