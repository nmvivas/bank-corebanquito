package com.banquito.core.bankdoc.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ObjectFactory;
import org.springframework.stereotype.Component;

import com.banquito.core.bankdoc.dto.BankDTO;
import com.banquito.core.bankdoc.model.Bank;

@Mapper(componentModel = "spring", uses = { BankMapper.BankFactory.class })
public interface BankMapper {
    BankDTO toBankDTO(Bank bank);

    Bank toBank(BankDTO bankDTO);

    @Component
    class BankFactory {
        @ObjectFactory
        public Bank create(BankDTO dto) {
            return new Bank(dto.getId(), dto.getCode(), dto.getName(), dto.getStartDate());
        }
    }
}
