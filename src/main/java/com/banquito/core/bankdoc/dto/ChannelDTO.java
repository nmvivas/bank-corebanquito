package com.banquito.core.bankdoc.dto;

import com.banquito.core.bankdoc.util.mapper.Default;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ChannelDTO {
    @Default
    private String id;
    private String code;
    private String codeBank;
    private String nameBank;
    private String name;
}