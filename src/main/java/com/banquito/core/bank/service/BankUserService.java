package com.banquito.core.bank.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.banquito.core.bank.model.BankUser;
import com.banquito.core.bank.repository.BankUserRepository;

@Service
public class BankUserService {

    private final BankUserRepository repository;

    public BankUserService(BankUserRepository repository) {
        this.repository = repository;
    }

    public BankUser obtainUserById(Long id) {
        Optional<BankUser> userOpt = this.repository.findById(id);
        if (userOpt.isPresent()) {
            return userOpt.get();
        } else {
            throw new RuntimeException("No existe el usuario con id: " + id);
        }
    }

    public BankUser obtainByUserName(String userName) {
        BankUser user = this.repository.findByUsername(userName);
        if (user != null) {
            return user;
        } else {
            throw new RuntimeException("No existe usuario con el userName:" + userName);
        }
    }

    public BankUser obtainByEmail(String email) {
        BankUser user = this.repository.findByEmail(email);
        if (user != null) {
            return user;
        } else {
            throw new RuntimeException("No existe usuario con el email:" + email);
        }
    }
}
