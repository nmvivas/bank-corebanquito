package com.banquito.core.bankdoc.repository;

import org.springframework.boot.autoconfigure.integration.IntegrationProperties.Channel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChannelRepository extends MongoRepository<Channel, String> {
}
