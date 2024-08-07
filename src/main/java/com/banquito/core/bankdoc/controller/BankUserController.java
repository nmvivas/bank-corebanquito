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
    public List<BankUser> obtenerTodosLosUsuarios() {
        return bankUserService.obtenerTodosLosUsuarios();
    }

    @GetMapping("/{uniqueId}")
    public Optional<BankUser> obtenerUsuarioPorUniqueId(@PathVariable String uniqueId) {
        return bankUserService.obtenerUsuarioPorUniqueId(uniqueId);
    }

    @PostMapping
    public BankUser crearUsuario(@RequestBody BankUser bankUser) {
        return bankUserService.crearUsuario(bankUser);
    }

    @PutMapping
    public BankUser actualizarUsuario(@RequestBody BankUser bankUser) {
        return bankUserService.actualizarUsuario(bankUser);
    }

    @DeleteMapping("/{id}")
    public void eliminarUsuario(@PathVariable String id) {
        bankUserService.eliminarUsuario(id);
    }

    @GetMapping("/email/{email}")
    public Optional<BankUser> obtenerUsuarioPorEmail(@PathVariable String email) {
        return bankUserService.obtenerUsuarioPorEmail(email);
    }

    @GetMapping("/username/{userName}")
    public Optional<BankUser> obtenerUsuarioPorUserName(@PathVariable String userName) {
        return bankUserService.obtenerUsuarioPorUserName(userName);
    }

    @GetMapping("/typeuser/{typeUser}")
    public Optional<BankUser> obtenerUsuarioPorTypeUser(@PathVariable String typeUser) {
        return bankUserService.obtenerUsuarioPorTypeUser(typeUser);
    }

    @GetMapping("/fullname/{fullName}")
    public Optional<BankUser> obtenerUsuarioPorFullName(@PathVariable String fullName) {
        return bankUserService.obtenerUsuarioPorFullName(fullName);
    }
}
