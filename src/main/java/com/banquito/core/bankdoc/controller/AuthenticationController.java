package com.banquito.core.bankdoc.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/bank-microservice/api/v1/auth")
@CrossOrigin(origins = "*")
@Tag(name = "Authentication", description = "Endpoints for authentication")
public class AuthenticationController {

}