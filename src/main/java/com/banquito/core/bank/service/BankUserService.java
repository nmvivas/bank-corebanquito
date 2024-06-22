package com.banquito.core.bank.service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import com.banquito.core.bank.controller.dto.BankUserDTO;
import com.banquito.core.bank.controller.dto.UserPasswordDTO;
import com.banquito.core.bank.model.Bank;
import com.banquito.core.bank.model.BankUser;
import com.banquito.core.bank.model.Role;
import com.banquito.core.bank.repository.BankRepository;
import com.banquito.core.bank.repository.BankUserRepository;
import com.banquito.core.bank.repository.RoleRepository;
import com.banquito.core.bank.util.mapper.BankUserMapper;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BankUserService {

    private final BankUserRepository repository;
    private final BankRepository bankRepository;
    private final RoleRepository roleRepository;
    private final BankUserMapper bankUserMapper;
    private final SecureRandom random = new SecureRandom();
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int PASSWORD_LENGTH = 10;

    public BankUserService(BankUserRepository repository, BankRepository bankRepository, RoleRepository roleRepository,
            BankUserMapper bankUserMapper) {
        this.repository = repository;
        this.bankRepository = bankRepository;
        this.roleRepository = roleRepository;
        this.bankUserMapper = bankUserMapper;
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
        BankUser user = this.repository.findByUsername(userName);
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

    public BankUserDTO create(BankUserDTO dto) {
        if (this.repository.findByUsername(dto.getUserName()) != null) {
            throw new RuntimeException("Usuario repetido");
        }
        if (this.repository.findByEmail(dto.getEmail()) != null) {
            throw new RuntimeException("Email repetido");
        }

        BankUser user = this.bankUserMapper.toPersistence(dto);

        List<Bank> banks = this.bankRepository.findAll();
        if (banks.isEmpty()) {
            throw new RuntimeException("No hay bancos disponibles");
        }
        Bank bank = banks.get(0);

        Optional<Role> roleOpt = this.roleRepository.findById(dto.getCodeRole());
        if (roleOpt.isEmpty()) {
            throw new RuntimeException("Rol no encontrado");
        }
        Role role = roleOpt.get();

        user.setCodeBank(bank.getCode());
        user.setTypeUser(dto.getTypeUser());
        user.setRole(role);
        user.setCreationDate(LocalDateTime.now());
        user.setLastLogin(LocalDateTime.now());

        // String generatedPassword = generateRandomPassword();
        // user.setPassword(DigestUtils.md5Hex(generatedPassword));
        String md5Hex = DigestUtils.md5Hex(dto.getPassword());
        user.setPassword(md5Hex);

        BankUser userCreated = this.repository.save(user);
        return this.bankUserMapper.toDTO(userCreated);
    }

    public List<BankUser> obtainByLastName(String lastName) {
        return this.repository.findTop100ByLastNameLikeOrderByLastNameAscFirstNameAsc(lastName);
    }

    public void changePassword(UserPasswordDTO userPassword) {
        BankUser user = this.repository.findByUsername(userPassword.getUserName());
        if (user == null) {
            throw new RuntimeException("No existe el usuario: " + userPassword.getUserName());
        }
        user.setPassword(userPassword.getPassword());
        String md5Hex2 = DigestUtils.md5Hex(userPassword.getPassword());
        user.setPassword(md5Hex2);
        this.repository.save(user);
    }

    public void generatePassword(String userName) {
        BankUser user = this.repository.findByUsername(userName);
        if (user == null) {
            throw new RuntimeException("No existe el usuario: " + userName);
        }
        String generatedPassword = generateRandomPassword();
        String md5Hex = DigestUtils.md5Hex(generatedPassword);
        user.setPassword(md5Hex);
        this.repository.save(user);
    }

    private String generateRandomPassword() {
        StringBuilder password = new StringBuilder(PASSWORD_LENGTH);
        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            password.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return password.toString();
    }
}
