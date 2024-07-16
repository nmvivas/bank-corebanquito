package com.banquito.core.bankdoc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.banquito.core.bankdoc.model.Bank;
import com.banquito.core.bankdoc.repository.BankRepository;

@Service
public class BankService {

    private final BankRepository bankRepository;

    public BankService(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public List<Bank> findAll() {
        return bankRepository.findAll();
    }

    public Bank findById(String id) {
        return bankRepository.findById(id).orElse(null);
    }

    public Bank save(Bank bank) {
        return bankRepository.save(bank);
    }

    public void deleteById(String id) {
        bankRepository.deleteById(id);
    }
}
