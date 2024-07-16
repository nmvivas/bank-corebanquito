package com.banquito.core.bankdoc.dto;

import lombok.Builder;
import lombok.Value;
import lombok.NoArgsConstructor;

import java.util.Date;

import lombok.AllArgsConstructor;

@Value
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class BankDTO {
    private String id;
    private String code;
    private String name;
    private String status;
    private Date startDate;
}