package com.banquito.core.bankdoc.controller;

import java.util.List;

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

@RestController
@RequestMapping("/api/v1/bankuser")
public class BankUserController {

    private final BankUserService bankUserService;

    public BankUserController(BankUserService bankUserService) {
        this.bankUserService = bankUserService;
    }

    @GetMapping
    public List<BankUser> getAllBankUsers() {
        return bankUserService.findAll();
    }

    @GetMapping("/{id}")
    public BankUser getBankUserById(@PathVariable String id) {
        return bankUserService.findById(id);
    }

    @PostMapping
    public BankUser createBankUser(@RequestBody BankUser bankUser) {
        return bankUserService.save(bankUser);
    }

    @PutMapping("/{id}")
    public BankUser updateBankUser(@PathVariable String id, @RequestBody BankUser bankUser) {
        bankUser.setId(id);
        return bankUserService.save(bankUser);
    }

    @DeleteMapping("/{id}")
    public void deleteBankUser(@PathVariable String id) {
        bankUserService.deleteById(id);
    }
}
