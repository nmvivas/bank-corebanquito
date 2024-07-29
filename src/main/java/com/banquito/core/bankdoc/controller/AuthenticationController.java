package com.banquito.core.bankdoc.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.bankdoc.dto.BankUserDTO;
import com.banquito.core.bankdoc.service.AuthenticationService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public BankUserDTO login(@RequestBody BankUserDTO bankUserDTO) {
        return authenticationService.login(bankUserDTO);
    }
}
