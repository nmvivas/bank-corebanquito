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

    public List<BankUser> obtenerTodosLosUsuarios() {
        return bankUserRepository.findAll();
    }

    public Optional<BankUser> obtenerUsuarioPorUniqueId(String uniqueId) {
        return bankUserRepository.findByUniqueId(uniqueId);
    }

    public BankUser crearUsuario(BankUser bankUser) {
        bankUser.setUniqueId(uniqueIdGeneration.generateUniqueId());
        bankUser.setPassword(passwordEncoder.encode(bankUser.getPassword()));
        return bankUserRepository.save(bankUser);
    }

    public BankUser actualizarUsuario(BankUser bankUser) {
        return bankUserRepository.save(bankUser);
    }

    public void eliminarUsuario(String id) {
        bankUserRepository.deleteById(id);
    }

    public Optional<BankUser> obtenerUsuarioPorEmail(String email) {
        return bankUserRepository.findByEmail(email);
    }

    public Optional<BankUser> obtenerUsuarioPorUserName(String userName) {
        return bankUserRepository.findByUserName(userName);
    }

    public Optional<BankUser> obtenerUsuarioPorTypeUser(String typeUser) {
        return bankUserRepository.findByTypeUser(typeUser);
    }

    public Optional<BankUser> obtenerUsuarioPorFullName(String fullName) {
        return bankUserRepository.findByFullName(fullName);
    }
}
