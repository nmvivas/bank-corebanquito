package com.banquito.core.bankdoc.util.mapper;

import org.mapstruct.Mapper;

import com.banquito.core.bankdoc.dto.BankDTO;
import com.banquito.core.bankdoc.model.Bank;

@Mapper(componentModel = "spring")
public interface BankMapper {
    BankDTO toBankDTO(Bank bank);

    Bank toBank(BankDTO bankDTO);
}
