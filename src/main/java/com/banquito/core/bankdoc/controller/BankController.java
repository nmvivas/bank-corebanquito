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
    public List<Bank> obtenerTodosLosBancos() {
        return bankService.obtenerTodosLosBancos();
    }

    @GetMapping("/{id}")
    public Optional<Bank> obtenerBancoPorId(@PathVariable String id) {
        return bankService.obtenerBancoPorId(id);
    }

    @PostMapping
    public Bank crearBanco(@RequestBody Bank bank) {
        return bankService.crearBanco(bank);
    }

    @PutMapping
    public Bank actualizarBanco(@RequestBody Bank bank) {
        return bankService.actualizarBanco(bank);
    }

    @DeleteMapping("/{id}")
    public void eliminarBanco(@PathVariable String id) {
        bankService.eliminarBanco(id);
    }
}
