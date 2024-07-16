package com.banquito.core.bankdoc.util.mapper;

import com.banquito.core.bankdoc.dto.BankDTO;
import com.banquito.core.bankdoc.model.Bank;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BankMapper {
    BankMapper INSTANCE = Mappers.getMapper(BankMapper.class);

    BankDTO toBankDTO(Bank bank);

    Bank toBank(BankDTO bankDTO);
}