package com.banquito.core.bankdoc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.banquito.core.bankdoc.model.BankUser;
import com.banquito.core.bankdoc.repository.BankUserRepository;

@Service
public class BankUserService {

    private final BankUserRepository bankUserRepository;

    public BankUserService(BankUserRepository bankUserRepository) {
        this.bankUserRepository = bankUserRepository;
    }

    public List<BankUser> findAll() {
        return bankUserRepository.findAll();
    }

    public BankUser findById(String id) {
        return bankUserRepository.findById(id).orElse(null);
    }

    public BankUser save(BankUser bankUser) {
        return bankUserRepository.save(bankUser);
    }

    public void deleteById(String id) {
        bankUserRepository.deleteById(id);
    }
}
