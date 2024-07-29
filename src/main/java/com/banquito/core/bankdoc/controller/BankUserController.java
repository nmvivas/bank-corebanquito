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
@RequestMapping("/api/v1/bankuser")
@CrossOrigin(origins = "*")
@Tag(name = "BankUser", description = "Endpoints for managing bank users")
public class BankUserController {

    private final BankUserService bankUserService;

    public BankUserController(BankUserService bankUserService) {
        this.bankUserService = bankUserService;
    }

    @GetMapping
    @Operation(summary = "Get all bank users", description = "Retrieve a list of all bank users")
    public List<BankUser> getAllBankUsers() {
        return bankUserService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get bank user by ID", description = "Retrieve a bank user by their ID")
    public BankUser getBankUserById(@PathVariable String id) {
        Optional<BankUser> user = bankUserService.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new RuntimeException("No existe el usuario con id: " + id);
        }
    }

    @PostMapping
    @Operation(summary = "Create a bank user", description = "Create a new bank user")
    public BankUser createBankUser(@RequestBody BankUser bankUser) {
        return bankUserService.save(bankUser);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a bank user", description = "Update an existing bank user")
    public BankUser updateBankUser(@PathVariable String id, @RequestBody BankUser bankUser) {
        bankUser.setId(id);
        return bankUserService.save(bankUser);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a bank user", description = "Delete a bank user by their ID")
    public void deleteBankUser(@PathVariable String id) {
        bankUserService.deleteById(id);
    }
}
