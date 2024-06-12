package com.banquito.core.bank.dto;

import java.util.Date;

import lombok.Data;

@Data
public class BankDTO {

    private String code;
    private String name;
    private Date startDate;

}