package com.banquito.core.bankdoc.service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.banquito.core.bankdoc.dto.BankUserDTO;
import com.banquito.core.bankdoc.model.Bank;
import com.banquito.core.bankdoc.model.BankUser;
import com.banquito.core.bankdoc.repository.BankRepository;
import com.banquito.core.bankdoc.repository.BankUserRepository;
import com.banquito.core.bankdoc.util.UniqueIdGeneration;
import com.banquito.core.bankdoc.util.mapper.BankUserMapper;

@Service
@Transactional
public class BankUserService {

    private final BankUserRepository bankUserRepository;
    private final BankRepository bankRepository;
    private final BankUserMapper bankUserMapper;
    private final UniqueIdGeneration uniqueIdGeneration;
    private final SecureRandom random = new SecureRandom();
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int PASSWORD_LENGTH = 10;

    public BankUserService(BankUserRepository bankUserRepository, BankRepository bankRepository,
            BankUserMapper bankUserMapper, UniqueIdGeneration uniqueIdGeneration) {
        this.bankUserRepository = bankUserRepository;
        this.bankRepository = bankRepository;
        this.bankUserMapper = bankUserMapper;
        this.uniqueIdGeneration = uniqueIdGeneration;
    }

    public List<BankUser> findAll() {
        return bankUserRepository.findAll();
    }

    public Optional<BankUser> findById(String id) {
        return bankUserRepository.findById(id);
    }

    public BankUser save(BankUser bankUser) {
        bankUser.setPassword(DigestUtils.md5DigestAsHex(bankUser.getPassword().getBytes()));
        return bankUserRepository.save(bankUser);
    }

    public void deleteById(String id) {
        bankUserRepository.deleteById(id);
    }

    public BankUser findByUsername(String userName) {
        BankUser user = this.bankUserRepository.findByUserName(userName);
        if (user != null) {
            return user;
        } else {
            throw new RuntimeException("No existe usuario con el userName: " + userName);
        }
    }

    public BankUser findByEmail(String email) {
        BankUser user = this.bankUserRepository.findByEmail(email);
        if (user != null) {
            return user;
        } else {
            throw new RuntimeException("No existe usuario con el email: " + email);
        }
    }

    public BankUserDTO create(BankUserDTO dto) {
        if (this.bankUserRepository.findByUserName(dto.getUserName()) != null) {
            throw new RuntimeException("Usuario repetido");
        }

        BankUser user = this.bankUserMapper.toBankUser(dto);

        List<Bank> banks = this.bankRepository.findAll();
        if (banks.isEmpty()) {
            throw new RuntimeException("No hay bancos disponibles");
        }
        Bank bank = banks.get(0);

        user.setCodeBank(bank.getCode());
        user.setTypeUser(dto.getTypeUser());
        user.setCreationDate(LocalDateTime.now());
        user.setLastLogin(LocalDateTime.now());
        user.setUniqueId(uniqueIdGeneration.generateUniqueId());

        user.setPassword(DigestUtils.md5DigestAsHex(dto.getPassword().getBytes()));

        BankUser userCreated = this.bankUserRepository.save(user);
        return this.bankUserMapper.toBankUserDTO(userCreated);
    }

    public List<BankUser> findByLastName(String lastName) {
        return this.bankUserRepository.findTop100ByLastNameLikeOrderByLastNameAsc(lastName);
    }

    public void changePassword(BankUserDTO userPassword) {
        BankUser user = this.bankUserRepository.findByUserName(userPassword.getUserName());
        if (user == null) {
            throw new RuntimeException("No existe el usuario: " + userPassword.getUserName());
        }
        String hashedPassword = DigestUtils.md5DigestAsHex(userPassword.getPassword().getBytes());
        user.setPassword(hashedPassword);
        this.bankUserRepository.save(user);
    }

    public void generatePassword(String userName) {
        BankUser user = this.bankUserRepository.findByUserName(userName);
        if (user == null) {
            throw new RuntimeException("No existe el usuario: " + userName);
        }
        String generatedPassword = generateRandomPassword();
        String hashedPassword = DigestUtils.md5DigestAsHex(generatedPassword.getBytes());
        user.setPassword(hashedPassword);
        this.bankUserRepository.save(user);
    }

    private String generateRandomPassword() {
        StringBuilder password = new StringBuilder(PASSWORD_LENGTH);
        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            password.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return password.toString();
    }
}
