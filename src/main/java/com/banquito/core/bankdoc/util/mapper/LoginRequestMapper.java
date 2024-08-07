package com.banquito.core.bankdoc.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.banquito.core.bankdoc.dto.LoginRequestDTO;
import com.banquito.core.bankdoc.model.BankUser;

@Mapper
public interface LoginRequestMapper {
    LoginRequestMapper INSTANCE = Mappers.getMapper(LoginRequestMapper.class);

    BankUser loginRequestDTOToBankUser(LoginRequestDTO loginRequestDTO);
}
