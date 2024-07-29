package com.banquito.core.bankdoc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ChannelDTO {
    private String id;
    private String code;
    private String codeBank;
    private String nameBank;
    private String name;
}