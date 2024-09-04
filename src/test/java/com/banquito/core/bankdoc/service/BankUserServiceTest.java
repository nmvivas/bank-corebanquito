package com.banquito.core.bankdoc.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.banquito.core.bankdoc.model.BankUser;
import com.banquito.core.bankdoc.repository.BankUserRepository;
import com.banquito.core.bankdoc.util.UniqueIdGeneration;

@ExtendWith(MockitoExtension.class)
public class BankUserServiceTest {

    @Mock
    private BankUserRepository bankUserRepository;

    @Mock
    private UniqueIdGeneration uniqueIdGeneration;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private BankUserService bankUserService;

    private BankUser bankUser;

    @BeforeEach
    void setUp() {
        bankUser = new BankUser();
        bankUser.setId("1");
        bankUser.setUniqueId("UNIQUE001");
        bankUser.setUserName("testUser");
        bankUser.setFirstName("John");
        bankUser.setLastName("Doe");
        bankUser.setFullName("John Doe");
        bankUser.setTypeUser("ADMIN");
        bankUser.setPassword("password");
        bankUser.setCreationDate(LocalDateTime.now());
        bankUser.setState("ACTIVE");
        bankUser.setLastLogin(LocalDateTime.now());
        bankUser.setEmail("test@example.com");

    }

    @Test
    void testGetAllUsers() {
        when(bankUserRepository.findAll()).thenReturn(Arrays.asList(bankUser));

        List<BankUser> users = bankUserService.getAllUsers();

        assertEquals(1, users.size());
        assertEquals("testUser", users.get(0).getUserName());

        verify(bankUserRepository, times(1)).findAll();
    }

    @Test
    void testGetUserByUniqueId() {
        when(bankUserRepository.findByUniqueId("UNIQUE001")).thenReturn(Optional.of(bankUser));

        Optional<BankUser> result = bankUserService.getUserByUniqueId("UNIQUE001");

        assertTrue(result.isPresent());
        assertEquals("testUser", result.get().getUserName());

        verify(bankUserRepository, times(1)).findByUniqueId("UNIQUE001");
    }

    @Test
    void testCreateUser() {
        when(uniqueIdGeneration.generateUniqueId()).thenReturn("UNIQUE002");
        when(bankUserRepository.save(any(BankUser.class))).thenReturn(bankUser);

        BankUser newUser = new BankUser();
        newUser.setUserName("newUser");
        newUser.setPassword("newPassword");

        BankUser createdUser = bankUserService.createUser(newUser);

        assertEquals("testUser", createdUser.getUserName());
        assertEquals("password", createdUser.getPassword());
        assertEquals("UNIQUE001", createdUser.getUniqueId());

        verify(bankUserRepository, times(1)).save(any(BankUser.class));
        verify(uniqueIdGeneration, times(1)).generateUniqueId();
    }
    
    
    @Test
    void testUpdateUser() {
        when(bankUserRepository.save(any(BankUser.class))).thenReturn(bankUser);

        bankUser.setFirstName("UpdatedName");
        BankUser updatedUser = bankUserService.updateUser(bankUser);

        assertEquals("UpdatedName", updatedUser.getFirstName());

        verify(bankUserRepository, times(1)).save(bankUser);
    }

    @Test
    void testDeleteUserByUniqueId() {
        when(bankUserRepository.findByUniqueId("UNIQUE001")).thenReturn(Optional.of(bankUser));

        bankUserService.deleteUserByUniqueId("UNIQUE001");

        verify(bankUserRepository, times(1)).findByUniqueId("UNIQUE001");
        verify(bankUserRepository, times(1)).deleteById("1");
    }

    @Test
    void testGetUserByEmail() {
        when(bankUserRepository.findByEmail("test@example.com")).thenReturn(Optional.of(bankUser));

        Optional<BankUser> result = bankUserService.getUserByEmail("test@example.com");

        assertTrue(result.isPresent());
        assertEquals("testUser", result.get().getUserName());

        verify(bankUserRepository, times(1)).findByEmail("test@example.com");
    }

    @Test
    void testGetUserByUserName() {
        when(bankUserRepository.findByUserName("testUser")).thenReturn(Optional.of(bankUser));

        Optional<BankUser> result = bankUserService.getUserByUserName("testUser");

        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getFullName());

        verify(bankUserRepository, times(1)).findByUserName("testUser");
    }

    @Test
    void testGetUserByTypeUser() {
        when(bankUserRepository.findByTypeUser("ADMIN")).thenReturn(Optional.of(bankUser));

        Optional<BankUser> result = bankUserService.getUserByTypeUser("ADMIN");

        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getFullName());

        verify(bankUserRepository, times(1)).findByTypeUser("ADMIN");
    }

    @Test
    void testGetUserByFullName() {
        when(bankUserRepository.findByFullName("John Doe")).thenReturn(Optional.of(bankUser));

        Optional<BankUser> result = bankUserService.getUserByFullName("John Doe");

        assertTrue(result.isPresent());
        assertEquals("testUser", result.get().getUserName());

        verify(bankUserRepository, times(1)).findByFullName("John Doe");
    }
}