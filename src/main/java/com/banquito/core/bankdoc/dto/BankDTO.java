package com.banquito.core.bankdoc.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class BankDTO {

    private String id;

    private String code;

    private String name;

    private Date startDate;
}
