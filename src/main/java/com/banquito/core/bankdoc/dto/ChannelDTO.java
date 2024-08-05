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
    private String name;

    public void setId(String id) {
        this.id = id;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public void setCodeBank(String codeBank) {
        this.codeBank = codeBank;
    }
    public void setName(String name) {
        this.name = name;
    }
}