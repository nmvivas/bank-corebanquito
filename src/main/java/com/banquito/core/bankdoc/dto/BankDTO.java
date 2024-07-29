package com.banquito.core.bankdoc.dto;

import java.util.Date;

import com.banquito.core.bankdoc.util.mapper.Default;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class BankDTO {
    @Default
    private String id;
    private String code;
    private String name;
    private Date startDate;
}