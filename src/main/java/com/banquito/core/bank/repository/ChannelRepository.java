package com.banquito.core.bank.repository;

import com.banquito.core.bank.model.Channel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChannelRepository extends JpaRepository<Channel, String> {

    List<Channel> findAllByOrderByName();

}
