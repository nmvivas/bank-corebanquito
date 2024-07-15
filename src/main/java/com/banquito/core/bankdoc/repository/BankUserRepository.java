package com.banquito.core.bankdoc.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.banquito.core.bankdoc.model.BankUser;
import java.util.List;

public interface BankUserRepository extends MongoRepository<BankUser, String> {

    List<BankUser> findByTypeUser(String typeUser);

    List<BankUser> findByState(String state);

    BankUser findByUserName(String userName);

    BankUser findByEmail(String email);
}
