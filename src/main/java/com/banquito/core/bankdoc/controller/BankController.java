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

import com.banquito.core.bankdoc.model.Bank;
import com.banquito.core.bankdoc.service.BankService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/bank-microservice/api/v1/bank")
@CrossOrigin(origins = "*")
@Tag(name = "Bank", description = "Endpoints for managing banks")
public class BankController {

    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping
    @Operation(summary = "Get All Banks", description = "Retrieve a list of all banks")
    public List<Bank> getAllBanks() {
        return bankService.getAllBanks();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Bank by ID", description = "Retrieve a bank by its ID")
    public Optional<Bank> getBankById(@PathVariable String id) {
        return bankService.getBankById(id);
    }

    @PostMapping
    @Operation(summary = "Create Bank", description = "Create a new bank")
    public Bank createBank(@RequestBody Bank bank) {
        return bankService.createBank(bank);
    }

    @PutMapping
    @Operation(summary = "Update Bank", description = "Update an existing bank")
    public Bank updateBank(@RequestBody Bank bank) {
        return bankService.updateBank(bank);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Bank", description = "Delete a bank by its ID")
    public void deleteBank(@PathVariable String id) {
        bankService.deleteBank(id);
    }
}
