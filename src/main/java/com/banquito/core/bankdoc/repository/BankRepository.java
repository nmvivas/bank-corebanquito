package com.banquito.core.bankdoc.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.banquito.core.bankdoc.model.Bank;

public interface BankRepository extends MongoRepository<Bank, String> {

}
