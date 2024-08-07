package com.banquito.core.bankdoc.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.banquito.core.bankdoc.model.BankUser;
import com.banquito.core.bankdoc.repository.BankUserRepository;
import com.banquito.core.bankdoc.util.UniqueIdGeneration;

@Service
public class BankUserService {

    private static final Logger logger = LoggerFactory.getLogger(BankUserService.class);

    private final BankUserRepository bankUserRepository;
    private final UniqueIdGeneration uniqueIdGeneration;
    private final PasswordEncoder passwordEncoder;

    public BankUserService(BankUserRepository bankUserRepository, UniqueIdGeneration uniqueIdGeneration) {
        this.bankUserRepository = bankUserRepository;
        this.uniqueIdGeneration = uniqueIdGeneration;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public List<BankUser> getAllUsers() {
        logger.info("Fetching all bank users");
        return bankUserRepository.findAll();
    }

    public Optional<BankUser> getUserByUniqueId(String uniqueId) {
        logger.info("Fetching bank user with uniqueId: {}", uniqueId);
        return bankUserRepository.findByUniqueId(uniqueId);
    }

    public BankUser createUser(BankUser bankUser) {
        logger.info("Creating new bank user with username: {}", bankUser.getUserName());
        bankUser.setUniqueId(uniqueIdGeneration.generateUniqueId());
        bankUser.setPassword(passwordEncoder.encode(bankUser.getPassword()));
        return bankUserRepository.save(bankUser);
    }

    public BankUser updateUser(BankUser bankUser) {
        logger.info("Updating bank user with uniqueId: {}", bankUser.getUniqueId());
        return bankUserRepository.save(bankUser);
    }

    public void deleteUserByUniqueId(String uniqueId) {
        logger.info("Deleting bank user with uniqueId: {}", uniqueId);
        Optional<BankUser> user = bankUserRepository.findByUniqueId(uniqueId);
        user.ifPresent(bankUser -> bankUserRepository.deleteById(bankUser.getId()));
    }

    public Optional<BankUser> getUserByEmail(String email) {
        logger.info("Fetching bank user with email: {}", email);
        return bankUserRepository.findByEmail(email);
    }

    public Optional<BankUser> getUserByUserName(String userName) {
        logger.info("Fetching bank user with username: {}", userName);
        return bankUserRepository.findByUserName(userName);
    }

    public Optional<BankUser> getUserByTypeUser(String typeUser) {
        logger.info("Fetching bank user with type: {}", typeUser);
        return bankUserRepository.findByTypeUser(typeUser);
    }

    public Optional<BankUser> getUserByFullName(String fullName) {
        logger.info("Fetching bank user with full name: {}", fullName);
        return bankUserRepository.findByFullName(fullName);
    }
}
