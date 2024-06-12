package com.banquito.core.bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.banquito.core.bank.model.BankUser;
import com.banquito.core.bank.repository.BankRepository;
import com.banquito.core.bank.repository.BankUserRepository;

import jakarta.transaction.Transactional;

@Service
public class BankUserService {

    private final BankUserRepository repository;
    private final BankRepository bankRepository;

    public BankUserService(BankUserRepository repository, BankRepository bankRepository) {
        this.repository = repository;
        this.bankRepository = bankRepository;
    }

    public List<BankUser> getAllBankUsers() {
        return this.repository.findAll();
    }

    @Transactional(Transactional.TxType.NEVER)
    public BankUser obtainUserById(Long id) {
        Optional<BankUser> userOpt = this.repository.findById(id);
        if (userOpt.isPresent()) {
            return userOpt.get();
        } else {
            throw new RuntimeException("No existe el usuario con id: " + id);
        }
    }

    public BankUser obtainByUserName(String userName) {
        BankUser user = this.repository.findByUserName(userName);
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

    public BankUser createBankUser(BankUser bankUser) {
        return this.repository.save(bankUser);
    }

    public BankUser updateBankUser(Long id, BankUser bankUser) {
        if (this.repository.existsById(id)) {
            return this.repository.save(bankUser);
        } else {
            throw new RuntimeException("No existe el usuario con id: " + id);
        }
    }

    public void deleteBankUser(Long id) {
        if (this.repository.existsById(id)) {
            this.repository.deleteById(id);
        } else {
            throw new RuntimeException("No existe el usuario con id: " + id);
        }
    }

    public BankUser validateUser(String userName, String password) {
        BankUser user = repository.findByUserName(userName);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            throw new RuntimeException("Usuario o contrasena invalidos");
        }
    }
}
