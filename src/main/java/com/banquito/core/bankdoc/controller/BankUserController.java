package com.banquito.core.bankdoc.controller;

import java.util.List;
import java.util.Optional;
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

import com.banquito.core.bankdoc.dto.BankUserDTO;
import com.banquito.core.bankdoc.model.BankUser;
import com.banquito.core.bankdoc.service.BankUserService;
import com.banquito.core.bankdoc.util.mapper.BankUserMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/bank-microservice/api/v1/bankuser")
@CrossOrigin(origins = "*")
@Tag(name = "BankUser", description = "Endpoints for managing bank users")
public class BankUserController {

    private final BankUserService bankUserService;
    private final BankUserMapper bankUserMapper;

    public BankUserController(BankUserService bankUserService, BankUserMapper bankUserMapper) {
        this.bankUserService = bankUserService;
        this.bankUserMapper = bankUserMapper;
    }

    @GetMapping
    @Operation(summary = "Get all bank users", description = "Retrieve a list of all bank users")
    public List<BankUserDTO> getAllBankUsers() {
        return bankUserService.findAll().stream().map(bankUserMapper::toBankUserDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get bank user by ID", description = "Retrieve a bank user by their ID")
    public BankUserDTO getBankUserById(@PathVariable String id) {
        Optional<BankUser> bankUser = bankUserService.findById(id);
        if (bankUser.isPresent()) {
            return bankUserMapper.toBankUserDTO(bankUser.get());
        } else {
            throw new RuntimeException("No existe el usuario con id: " + id);
        }
    }

    @PostMapping
    @Operation(summary = "Create a bank user", description = "Create a new bank user")
    public BankUserDTO createBankUser(@RequestBody BankUserDTO bankUserDTO) {
        return bankUserMapper.toBankUserDTO(bankUserService.save(bankUserMapper.toBankUser(bankUserDTO)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a bank user", description = "Update an existing bank user")
    public BankUserDTO updateBankUser(@PathVariable String id, @RequestBody BankUserDTO bankUserDTO) {
        bankUserDTO.setId(id);
        return bankUserMapper.toBankUserDTO(bankUserService.save(bankUserMapper.toBankUser(bankUserDTO)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a bank user", description = "Delete a bank user by their ID")
    public void deleteBankUser(@PathVariable String id) {
        bankUserService.deleteById(id);
    }
}
