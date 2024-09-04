package com.banquito.core.bankdoc.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.banquito.core.bankdoc.model.Channel;
@Repository
public interface ChannelRepository extends MongoRepository<Channel, String> {
}
