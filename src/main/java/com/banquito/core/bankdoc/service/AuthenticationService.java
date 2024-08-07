package com.banquito.core.bankdoc.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.banquito.core.bankdoc.model.BankUser;
import com.banquito.core.bankdoc.repository.BankUserRepository;

@Service
public class AuthenticationService {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    private final BankUserRepository bankUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(BankUserRepository bankUserRepository, PasswordEncoder passwordEncoder) {
        this.bankUserRepository = bankUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<BankUser> login(String userName, String password) {
        logger.info("Attempting to login user: {}", userName);
        Optional<BankUser> bankUser = bankUserRepository.findByUserName(userName);
        if (bankUser.isPresent() && passwordEncoder.matches(password, bankUser.get().getPassword())) {
            logger.info("Login successful for user: {}", userName);
            return bankUser;
        }
        logger.warn("Login failed for user: {}", userName);
        return Optional.empty();
    }
}
