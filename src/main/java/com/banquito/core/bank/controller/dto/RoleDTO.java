package com.banquito.core.bank.controller.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RoleDTO {

    private String code;
    private String name;

}
