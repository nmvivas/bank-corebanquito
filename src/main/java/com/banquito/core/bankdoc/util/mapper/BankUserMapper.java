package com.banquito.core.bankdoc.util.mapper;

import org.mapstruct.Mapper;

import com.banquito.core.bankdoc.dto.BankUserDTO;
import com.banquito.core.bankdoc.model.BankUser;

@Mapper(componentModel = "spring")
public interface BankUserMapper {
    BankUserDTO toBankUserDTO(BankUser bankUser);

    BankUser toBankUser(BankUserDTO bankUserDTO);
}
