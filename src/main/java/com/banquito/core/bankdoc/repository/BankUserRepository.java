package com.banquito.core.bankdoc.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.banquito.core.bankdoc.model.BankUser;

public interface BankUserRepository extends MongoRepository<BankUser, String> {
    BankUser findByUserName(String userName);

    BankUser findByEmail(String email);

    List<BankUser> findTop100ByLastNameLikeOrderByLastNameAsc(String lastName); // Corrige esta línea
}
