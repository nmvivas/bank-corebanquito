package com.banquito.core.bankdoc.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.bankdoc.model.Bank;
import com.banquito.core.bankdoc.service.BankService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/bank")
@CrossOrigin(origins = "*")
@Tag(name = "Bank", description = "Endpoints for managing banks")
public class BankController {

    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping
    @Operation(summary = "Get all banks", description = "Retrieve a list of all banks")
    public List<Bank> getAllBanks() {
        return bankService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get bank by ID", description = "Retrieve a bank by its ID")
    public Bank getBankById(@PathVariable String id) {
        return bankService.findById(id);
    }

    @PostMapping
    @Operation(summary = "Create a bank", description = "Create a new bank")
    public Bank createBank(@RequestBody Bank bank) {
        return bankService.save(bank);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a bank", description = "Update an existing bank")
    public Bank updateBank(@PathVariable String id, @RequestBody Bank bank) {
        bank.setId(id);
        return bankService.save(bank);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a bank", description = "Delete a bank by its ID")
    public void deleteBank(@PathVariable String id) {
        bankService.deleteById(id);
    }
}
