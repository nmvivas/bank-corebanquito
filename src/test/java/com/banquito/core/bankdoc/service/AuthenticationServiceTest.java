package com.banquito.core.bankdoc.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.banquito.core.bankdoc.model.BankUser;
import com.banquito.core.bankdoc.repository.BankUserRepository;
import com.banquito.core.bankdoc.service.AuthenticationService;

@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest {
    @Mock
    private BankUserRepository bankUserRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthenticationService authenticationService;

    private BankUser bankUser;

    @BeforeEach
    void setUp() {
        bankUser = new BankUser();
        bankUser.setId("1");
        bankUser.setCodeBank("BANCOD001");
        bankUser.setUniqueId("DRT0034855");
        bankUser.setUserName("testUser");
        bankUser.setFirstName("Test");
        bankUser.setLastName("User");
        bankUser.setFullName("Test User");
        bankUser.setTypeUser("BAC");
        bankUser.setPassword("encodedPassword");
        bankUser.setCreationDate(LocalDateTime.now());
        bankUser.setState("ACT");
        bankUser.setLastLogin(LocalDateTime.now());
        bankUser.setEmail("testuser@example.com");
    }

    @Test
    void testLoginSuccess() {
        when(bankUserRepository.findByUserName("testUser")).thenReturn(Optional.of(bankUser));
        when(passwordEncoder.matches("correctPassword", "encodedPassword")).thenReturn(true);

        Optional<BankUser> result = authenticationService.login("testUser", "correctPassword");

        assertTrue(result.isPresent());
        assertTrue(result.get().getUserName().equals("testUser"));

        verify(bankUserRepository, times(1)).findByUserName("testUser");
        verify(passwordEncoder, times(1)).matches("correctPassword", "encodedPassword");
    }

    @Test
    void testLoginFailureInvalidPassword() {
        when(bankUserRepository.findByUserName("testUser")).thenReturn(Optional.of(bankUser));
        when(passwordEncoder.matches("wrongPassword", "encodedPassword")).thenReturn(false);

        Optional<BankUser> result = authenticationService.login("testUser", "wrongPassword");

        assertFalse(result.isPresent());

        verify(bankUserRepository, times(1)).findByUserName("testUser");
        verify(passwordEncoder, times(1)).matches("wrongPassword", "encodedPassword");
    }

    @Test
    void testLoginFailureUserNotFound() {
        when(bankUserRepository.findByUserName("nonExistentUser")).thenReturn(Optional.empty());

        Optional<BankUser> result = authenticationService.login("nonExistentUser", "anyPassword");

        assertFalse(result.isPresent());

        verify(bankUserRepository, times(1)).findByUserName("nonExistentUser");
    }
}
