package com.banquito.core.bankdoc.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.banquito.core.bankdoc.dto.BankUserDTO;
import com.banquito.core.bankdoc.model.BankUser;
import com.banquito.core.bankdoc.repository.BankUserRepository;
import com.banquito.core.bankdoc.util.mapper.BankUserMapper;

@Service
public class AuthenticationService {

    private final BankUserRepository bankUserRepository;
    private final BankUserMapper bankUserMapper;

    public AuthenticationService(BankUserRepository bankUserRepository, BankUserMapper bankUserMapper) {
        this.bankUserRepository = bankUserRepository;
        this.bankUserMapper = bankUserMapper;
    }

    public BankUserDTO login(BankUserDTO dto) {
        String errorMessage = "Credenciales invalidas";
        BankUser user = this.bankUserRepository.findByUserName(dto.getUserName());
        String hashedPassword = DigestUtils.md5DigestAsHex(dto.getPassword().getBytes());
        if (user != null && user.getPassword().equals(hashedPassword)) {
            user.setLastLogin(LocalDateTime.now());
            this.bankUserRepository.save(user);
            if ("ACT".equals(user.getState())) {
                return this.bankUserMapper.toBankUserDTO(user);
            } else {
                errorMessage = "Usuario no es activo";
            }
        }
        throw new RuntimeException(errorMessage);
    }
}
