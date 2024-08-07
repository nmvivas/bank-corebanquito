package com.banquito.core.bankdoc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.banquito.core.bankdoc.model.Channel;
import com.banquito.core.bankdoc.repository.ChannelRepository;

@Service
public class ChannelService {

    private final ChannelRepository channelRepository;

    public ChannelService(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }

    public List<Channel> getAllChannels() {
        return channelRepository.findAll();
    }

    public Optional<Channel> getChannelById(String id) {
        return channelRepository.findById(id);
    }

    public Channel createChannel(Channel channel) {
        return channelRepository.save(channel);
    }

    public Channel updateChannel(Channel channel) {
        return channelRepository.save(channel);
    }

    public void deleteChannel(String id) {
        channelRepository.deleteById(id);
    }
}
