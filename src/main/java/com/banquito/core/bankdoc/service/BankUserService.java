package com.banquito.core.bankdoc.service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(BankUserService.class);

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
        logger.debug("Saving BankUser: {}", bankUser);
        bankUser.setPassword(DigestUtils.md5DigestAsHex(bankUser.getPassword().getBytes()));
        BankUser savedUser = bankUserRepository.save(bankUser);
        logger.debug("Saved BankUser: {}", savedUser);
        return savedUser;
    }

    public void deleteById(String id) {
        logger.debug("Deleting BankUser with id: {}", id);
        bankUserRepository.deleteById(id);
    }

    public BankUser findByUsername(String userName) {
        logger.debug("Finding BankUser by username: {}", userName);
        BankUser user = this.bankUserRepository.findByUserName(userName);
        if (user != null) {
            logger.debug("Found BankUser: {}", user);
            return user;
        } else {
            throw new RuntimeException("No existe usuario con el userName: " + userName);
        }
    }

    public BankUser findByEmail(String email) {
        logger.debug("Finding BankUser by email: {}", email);
        BankUser user = this.bankUserRepository.findByEmail(email);
        if (user != null) {
            logger.debug("Found BankUser: {}", user);
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
        String uniqueId = uniqueIdGeneration.generateUniqueId();
        user.setUniqueId(uniqueId);

        // Log para depuración
        System.out.println("Generated Unique ID: " + uniqueId);

        user.setPassword(DigestUtils.md5DigestAsHex(dto.getPassword().getBytes()));

        BankUser userCreated = this.bankUserRepository.save(user);
        return this.bankUserMapper.toBankUserDTO(userCreated);
    }
    
    public List<BankUser> findByLastName(String lastName) {
        logger.debug("Finding BankUsers by last name like: {}", lastName);
        return this.bankUserRepository.findTop100ByLastNameLikeOrderByLastNameAsc(lastName);
    }

    public void changePassword(BankUserDTO userPassword) {
        logger.debug("Changing password for user: {}", userPassword.getUserName());
        BankUser user = this.bankUserRepository.findByUserName(userPassword.getUserName());
        if (user == null) {
            throw new RuntimeException("No existe el usuario: " + userPassword.getUserName());
        }
        String hashedPassword = DigestUtils.md5DigestAsHex(userPassword.getPassword().getBytes());
        user.setPassword(hashedPassword);
        this.bankUserRepository.save(user);
        logger.debug("Changed password for user: {}", user);
    }

    public void generatePassword(String userName) {
        logger.debug("Generating password for user: {}", userName);
        BankUser user = this.bankUserRepository.findByUserName(userName);
        if (user == null) {
            throw new RuntimeException("No existe el usuario: " + userName);
        }
        String generatedPassword = generateRandomPassword();
        String hashedPassword = DigestUtils.md5DigestAsHex(generatedPassword.getBytes());
        user.setPassword(hashedPassword);
        this.bankUserRepository.save(user);
        logger.debug("Generated and set new password for user: {}", user);
    }

    private String generateRandomPassword() {
        StringBuilder password = new StringBuilder(PASSWORD_LENGTH);
        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            password.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return password.toString();
    }
}