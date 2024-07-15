package com.banquito.core.bankdoc.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.banquito.core.bankdoc.model.Bank;
import java.util.List;

public interface BankRepository extends MongoRepository<Bank, String> {

    List<Bank> findByNameOrderByName(String name);

    Bank findFirstByCode(String code);
}
