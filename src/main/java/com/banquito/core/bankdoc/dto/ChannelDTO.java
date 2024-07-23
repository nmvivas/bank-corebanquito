package com.banquito.core.bankdoc.dto;

import lombok.Builder;
import lombok.Value;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Value
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class ChannelDTO {
    private String id;
    private String code;
    private String codeBank;
    private String nameBank;
    private String name;
}
