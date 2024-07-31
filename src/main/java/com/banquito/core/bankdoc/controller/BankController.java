package com.banquito.core.bankdoc.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.bankdoc.dto.BankDTO;
import com.banquito.core.bankdoc.service.BankService;
import com.banquito.core.bankdoc.util.mapper.BankMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/bank")
@CrossOrigin(origins = "*")
@Tag(name = "Bank", description = "Endpoints for managing banks")
public class BankController {

    private final BankService bankService;
    private final BankMapper bankMapper;

    public BankController(BankService bankService, BankMapper bankMapper) {
        this.bankService = bankService;
        this.bankMapper = bankMapper;
    }

    @GetMapping
    @Operation(summary = "Get all banks", description = "Retrieve a list of all banks")
    public List<BankDTO> getAllBanks() {
        return bankService.findAll().stream().map(bankMapper::toBankDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get bank by ID", description = "Retrieve a bank by its ID")
    public BankDTO getBankById(@PathVariable String id) {
        return bankMapper.toBankDTO(bankService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Create a bank", description = "Create a new bank")
    public BankDTO createBank(@RequestBody BankDTO bankDTO) {
        return bankMapper.toBankDTO(bankService.save(bankMapper.toBank(bankDTO)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a bank", description = "Update an existing bank")
    public BankDTO updateBank(@PathVariable String id, @RequestBody BankDTO bankDTO) {
        bankDTO.setId(id);
        return bankMapper.toBankDTO(bankService.save(bankMapper.toBank(bankDTO)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a bank", description = "Delete a bank by its ID")
    public void deleteBank(@PathVariable String id) {
        bankService.deleteById(id);
    }
}
