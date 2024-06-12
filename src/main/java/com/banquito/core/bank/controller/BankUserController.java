package com.banquito.core.bank.controller;

import com.banquito.core.bank.dto.BankUserDTO;
import com.banquito.core.bank.model.BankUser;
import com.banquito.core.bank.service.BankUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/bankuser")
public class BankUserController {

    @Autowired
    private BankUserService bankUserService;

    @GetMapping
    public ResponseEntity<List<BankUser>> getAllBankUsers() {
        return ResponseEntity.ok(this.bankUserService.getAllBankUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankUser> getBankUserById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(this.bankUserService.obtainUserById(id));
        } catch (RuntimeException rte) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<BankUser> getByUserName(@PathVariable String username) {
        try {
            return ResponseEntity.ok(this.bankUserService.obtainByUserName(username));
        } catch (RuntimeException rte) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<BankUser> getByEmail(@PathVariable String email) {
        try {
            return ResponseEntity.ok(this.bankUserService.obtainByEmail(email));
        } catch (RuntimeException rte) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<BankUser> createBankUser(@RequestBody BankUser bankUser) {
        return ResponseEntity.ok(this.bankUserService.createBankUser(bankUser));
    }

    // @PostMapping
    // public ResponseEntity<BankUserDTO> createUser(@RequestBody BankUserDTO dto) {
    // this.service.create(dto);
    // }

    @PutMapping("/{id}")
    public ResponseEntity<BankUser> updateBankUser(@PathVariable Long id, @RequestBody BankUser bankUser) {
        return ResponseEntity.ok(this.bankUserService.updateBankUser(id, bankUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBankUser(@PathVariable Long id) {
        this.bankUserService.deleteBankUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<BankUser> login(@RequestParam String userName, @RequestParam String password) {
        try {
            BankUser user = bankUserService.validateUser(userName, password);
            return ResponseEntity.ok(user);
        } catch (RuntimeException rte) {
            return ResponseEntity.status(401).build();
        }
    }

    @GetMapping("/client/{username}")
    public ResponseEntity<BankUser> getClientByUsername(@PathVariable String userName) {
        try {
            BankUser user = bankUserService.obtainByUserName(userName);
            return ResponseEntity.ok(user);
        } catch (RuntimeException rte) {
            return ResponseEntity.notFound().build();
        }
    }
}
