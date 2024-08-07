package com.banquito.core.bankdoc.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.bankdoc.model.BankUser;
import com.banquito.core.bankdoc.service.BankUserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/bank-microservice/api/v1/bankuser")
@CrossOrigin(origins = "*")
@Tag(name = "BankUser", description = "Endpoints for managing bank users")
public class BankUserController {

    private final BankUserService bankUserService;

    public BankUserController(BankUserService bankUserService) {
        this.bankUserService = bankUserService;
    }

    @GetMapping
    @Operation(summary = "Get All Users", description = "Retrieve a list of all bank users")
    public List<BankUser> getAllUsers() {
        return bankUserService.getAllUsers();
    }

    @GetMapping("/{uniqueId}")
    @Operation(summary = "Get User by Unique ID", description = "Retrieve a bank user by their unique ID")
    public Optional<BankUser> getUserByUniqueId(@PathVariable String uniqueId) {
        return bankUserService.getUserByUniqueId(uniqueId);
    }

    @PostMapping
    @Operation(summary = "Create User", description = "Create a new bank user")
    public BankUser createUser(@RequestBody BankUser bankUser) {
        return bankUserService.createUser(bankUser);
    }

    @PutMapping
    @Operation(summary = "Update User", description = "Update an existing bank user")
    public BankUser updateUser(@RequestBody BankUser bankUser) {
        return bankUserService.updateUser(bankUser);
    }

    @DeleteMapping("/{uniqueId}")
    @Operation(summary = "Delete User by Unique ID", description = "Delete a bank user by their unique ID")
    public void deleteUserByUniqueId(@PathVariable String uniqueId) {
        bankUserService.deleteUserByUniqueId(uniqueId);
    }

    @GetMapping("/email/{email}")
    @Operation(summary = "Get User by Email", description = "Retrieve a bank user by their email")
    public Optional<BankUser> getUserByEmail(@PathVariable String email) {
        return bankUserService.getUserByEmail(email);
    }

    @GetMapping("/username/{userName}")
    @Operation(summary = "Get User by Username", description = "Retrieve a bank user by their username")
    public Optional<BankUser> getUserByUserName(@PathVariable String userName) {
        return bankUserService.getUserByUserName(userName);
    }

    @GetMapping("/typeuser/{typeUser}")
    @Operation(summary = "Get User by Type", description = "Retrieve a bank user by their type")
    public Optional<BankUser> getUserByTypeUser(@PathVariable String typeUser) {
        return bankUserService.getUserByTypeUser(typeUser);
    }

    @GetMapping("/fullname/{fullName}")
    @Operation(summary = "Get User by Full Name", description = "Retrieve a bank user by their full name")
    public Optional<BankUser> getUserByFullName(@PathVariable String fullName) {
        return bankUserService.getUserByFullName(fullName);
    }
}
