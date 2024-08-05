package com.banquito.core.bankdoc.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.banquito.core.bankdoc.model.Channel;

public interface ChannelRepository extends MongoRepository<Channel, String> {
    List<Channel> findAllByOrderByNameAsc();
}
