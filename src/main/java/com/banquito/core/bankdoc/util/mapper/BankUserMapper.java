package com.banquito.core.bankdoc.util.mapper;

import com.banquito.core.bankdoc.dto.BankUserDTO;
import com.banquito.core.bankdoc.model.BankUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BankUserMapper {
    BankUserMapper INSTANCE = Mappers.getMapper(BankUserMapper.class);

    BankUserDTO toBankUserDTO(BankUser bankUser);

    BankUser toBankUser(BankUserDTO bankUserDTO);
}
