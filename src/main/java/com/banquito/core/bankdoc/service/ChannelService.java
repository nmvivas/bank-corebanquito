package com.banquito.core.bankdoc.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.banquito.core.bankdoc.model.Channel;
import com.banquito.core.bankdoc.repository.ChannelRepository;

@Service
@Transactional
public class ChannelService {

    private final ChannelRepository channelRepository;

    public ChannelService(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }

    public List<Channel> findAll() {
        return channelRepository.findAll();
    }

    public Channel findById(String id) {
        return channelRepository.findById(id).orElse(null);
    }

    public Channel save(Channel channel) {
        return channelRepository.save(channel);
    }

    public void deleteById(String id) {
        channelRepository.deleteById(id);
    }
}
