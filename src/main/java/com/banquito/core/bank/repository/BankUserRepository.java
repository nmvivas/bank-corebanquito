package com.banquito.core.bank.repository;

import com.banquito.core.bank.model.BankUser;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BankUserRepository extends JpaRepository<BankUser, Long> {

    BankUser findByUserName(String username);

    BankUser findByEmail(String email);
}
