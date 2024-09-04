package com.banquito.core.bankdoc.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.banquito.core.bankdoc.dto.LoginRequestDTO;
import com.banquito.core.bankdoc.model.BankUser;
import com.banquito.core.bankdoc.repository.BankUserRepository;
import com.banquito.core.bankdoc.service.AuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(AuthenticationController.class)
public class AuthenticationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationService authenticationService;
    
    @MockBean
    private BankUserRepository bankUserRepository;  // Mock the repository

    private BankUser bankUser;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        bankUser = new BankUser();
        bankUser.setId("1");
        bankUser.setUserName("testUser");
        bankUser.setPassword("testPassword");
        bankUser.setFullName("Test User");
        bankUser.setCodeBank("BANK001");
        bankUser.setUniqueId("UNIQUEID001");
    }

    @Test
    void testLoginSuccess() throws Exception {
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setUserName("testUser");
        loginRequestDTO.setPassword("testPassword");

        when(authenticationService.login(anyString(), anyString())).thenReturn(Optional.of(bankUser));

        mockMvc.perform(post("/bank-microservice/api/v1/auth/login")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(loginRequestDTO)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(bankUser)));
    }
}