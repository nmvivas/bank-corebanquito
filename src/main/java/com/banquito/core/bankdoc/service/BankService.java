package com.banquito.core.bankdoc.service;

import org.springframework.stereotype.Service;

import com.banquito.core.bankdoc.repository.BankRepository;

@Service
public class BankService {

    private final BankRepository bankRepository;

    public BankService(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

}
