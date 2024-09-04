package com.banquito.core.bankdoc.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.banquito.core.bankdoc.model.BankUser;

@Repository
public interface BankUserRepository extends MongoRepository<BankUser, String> {
    Optional<BankUser> findByUniqueId(String uniqueId);
    Optional<BankUser> findByEmail(String email);
    Optional<BankUser> findByUserName(String userName);
    Optional<BankUser> findByTypeUser(String typeUser);
    Optional<BankUser> findByFullName(String fullName);
}
