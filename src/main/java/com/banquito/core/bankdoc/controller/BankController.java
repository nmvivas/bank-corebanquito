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

@RestController
@RequestMapping("/api/v1/bank")
@CrossOrigin(origins = "*")
public class BankController {

    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping
    public List<Bank> getAllBanks() {
        return bankService.findAll();
    }

    @GetMapping("/{id}")
    public Bank getBankById(@PathVariable String id) {
        return bankService.findById(id);
    }

    @PostMapping
    public Bank createBank(@RequestBody Bank bank) {
        return bankService.save(bank);
    }

    @PutMapping("/{id}")
    public Bank updateBank(@PathVariable String id, @RequestBody Bank bank) {
        bank.setId(id);
        return bankService.save(bank);
    }

    @DeleteMapping("/{id}")
    public void deleteBank(@PathVariable String id) {
        bankService.deleteById(id);
    }
}
