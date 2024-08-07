package com.banquito.core.bankdoc.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.banquito.core.bankdoc.model.BankUser;
import com.banquito.core.bankdoc.repository.BankUserRepository;

@Service
public class AuthenticationService {

    private final BankUserRepository bankUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(BankUserRepository bankUserRepository, PasswordEncoder passwordEncoder) {
        this.bankUserRepository = bankUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<BankUser> login(String userName, String password) {
        Optional<BankUser> bankUser = bankUserRepository.findByUserName(userName);
        if (bankUser.isPresent() && passwordEncoder.matches(password, bankUser.get().getPassword())) {
            return bankUser;
        }
        return Optional.empty();
    }
}
