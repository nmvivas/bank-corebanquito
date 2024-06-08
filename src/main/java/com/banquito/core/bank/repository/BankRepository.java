package com.banquito.core.bank.repository;

import com.banquito.core.bank.model.Bank;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank, String> {

}
