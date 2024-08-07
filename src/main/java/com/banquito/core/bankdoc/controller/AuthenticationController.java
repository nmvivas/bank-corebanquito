package com.banquito.core.bankdoc.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.bankdoc.dto.LoginRequestDTO;
import com.banquito.core.bankdoc.model.BankUser;
import com.banquito.core.bankdoc.service.AuthenticationService;
import com.banquito.core.bankdoc.util.mapper.LoginRequestMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/bank-microservice/api/v1/auth")
@CrossOrigin(origins = "*")
@Tag(name = "Authentication", description = "Endpoints for authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final LoginRequestMapper loginRequestMapper = LoginRequestMapper.INSTANCE;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    @Operation(summary = "Login", description = "Authenticate a user with username and password")
    public Optional<BankUser> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        BankUser bankUser = loginRequestMapper.loginRequestDTOToBankUser(loginRequestDTO);
        return authenticationService.login(bankUser.getUserName(), loginRequestDTO.getPassword());
    }
}
