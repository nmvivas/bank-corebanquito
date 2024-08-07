package com.banquito.core.bankdoc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.banquito.core.bankdoc.model.BankUser;
import com.banquito.core.bankdoc.repository.BankUserRepository;
import com.banquito.core.bankdoc.util.UniqueIdGeneration;

@Service
public class BankUserService {

    private final BankUserRepository bankUserRepository;
    private final UniqueIdGeneration uniqueIdGeneration;
    private final PasswordEncoder passwordEncoder;

    public BankUserService(BankUserRepository bankUserRepository, UniqueIdGeneration uniqueIdGeneration) {
        this.bankUserRepository = bankUserRepository;
        this.uniqueIdGeneration = uniqueIdGeneration;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public List<BankUser> getAllUsers() {
        return bankUserRepository.findAll();
    }

    public Optional<BankUser> getUserByUniqueId(String uniqueId) {
        return bankUserRepository.findByUniqueId(uniqueId);
    }

    public BankUser createUser(BankUser bankUser) {
        bankUser.setUniqueId(uniqueIdGeneration.generateUniqueId());
        bankUser.setPassword(passwordEncoder.encode(bankUser.getPassword()));
        return bankUserRepository.save(bankUser);
    }

    public BankUser updateUser(BankUser bankUser) {
        return bankUserRepository.save(bankUser);
    }

    public void deleteUserByUniqueId(String uniqueId) {
        Optional<BankUser> user = bankUserRepository.findByUniqueId(uniqueId);
        user.ifPresent(bankUser -> bankUserRepository.deleteById(bankUser.getId()));
    }

    public Optional<BankUser> getUserByEmail(String email) {
        return bankUserRepository.findByEmail(email);
    }

    public Optional<BankUser> getUserByUserName(String userName) {
        return bankUserRepository.findByUserName(userName);
    }

    public Optional<BankUser> getUserByTypeUser(String typeUser) {
        return bankUserRepository.findByTypeUser(typeUser);
    }

    public Optional<BankUser> getUserByFullName(String fullName) {
        return bankUserRepository.findByFullName(fullName);
    }
}
