package com.banquito.core.bankdoc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.banquito.core.bankdoc.model.Bank;
import com.banquito.core.bankdoc.repository.BankRepository;

@Service
public class BankService {

    private final BankRepository bankRepository;

    public BankService(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public List<Bank> obtenerTodosLosBancos() {
        return bankRepository.findAll();
    }

    public Optional<Bank> obtenerBancoPorId(String id) {
        return bankRepository.findById(id);
    }

    public Bank crearBanco(Bank bank) {
        return bankRepository.save(bank);
    }

    public Bank actualizarBanco(Bank bank) {
        return bankRepository.save(bank);
    }

    public void eliminarBanco(String id) {
        bankRepository.deleteById(id);
    }
}
