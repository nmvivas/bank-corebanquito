package com.banquito.core.bankdoc.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.bankdoc.model.BankUser;
import com.banquito.core.bankdoc.service.AuthenticationService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/bank-microservice/api/v1/auth")
@CrossOrigin(origins = "*")
@Tag(name = "Authentication", description = "Endpoints for authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public Optional<BankUser> login(@RequestParam String userName, @RequestParam String password) {
        return authenticationService.login(userName, password);
    }
}
