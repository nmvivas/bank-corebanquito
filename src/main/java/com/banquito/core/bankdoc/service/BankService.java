package com.banquito.core.bankdoc.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.banquito.core.bankdoc.model.Bank;
import com.banquito.core.bankdoc.repository.BankRepository;

@Service
public class BankService {

    private static final Logger logger = LoggerFactory.getLogger(BankService.class);

    private final BankRepository bankRepository;

    public BankService(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public List<Bank> getAllBanks() {
        logger.info("Fetching all banks");
        return bankRepository.findAll();
    }

    public Optional<Bank> getBankById(String id) {
        logger.info("Fetching bank with id: {}", id);
        return bankRepository.findById(id);
    }

    public Bank createBank(Bank bank) {
        logger.info("Creating new bank with code: {}", bank.getCode());
        return bankRepository.save(bank);
    }

    public Bank updateBank(Bank bank) {
        logger.info("Updating bank with id: {}", bank.getId());
        return bankRepository.save(bank);
    }

    public void deleteBank(String id) {
        logger.info("Deleting bank with id: {}", id);
        bankRepository.deleteById(id);
    }
}
