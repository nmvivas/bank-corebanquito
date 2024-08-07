package com.banquito.core.bankdoc.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.banquito.core.bankdoc.model.BankUser;

public interface BankUserRepository extends MongoRepository<BankUser, String> {

}
