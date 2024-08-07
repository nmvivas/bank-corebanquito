package com.banquito.core.bankdoc.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.banquito.core.bankdoc.model.Channel;
import com.banquito.core.bankdoc.repository.ChannelRepository;

@Service
public class ChannelService {

    private static final Logger logger = LoggerFactory.getLogger(ChannelService.class);

    private final ChannelRepository channelRepository;

    public ChannelService(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }

    public List<Channel> getAllChannels() {
        logger.info("Fetching all channels");
        return channelRepository.findAll();
    }

    public Optional<Channel> getChannelById(String id) {
        logger.info("Fetching channel with id: {}", id);
        return channelRepository.findById(id);
    }

    public Channel createChannel(Channel channel) {
        logger.info("Creating new channel with code: {}", channel.getCode());
        return channelRepository.save(channel);
    }

    public Channel updateChannel(Channel channel) {
        logger.info("Updating channel with id: {}", channel.getId());
        return channelRepository.save(channel);
    }

    public void deleteChannel(String id) {
        logger.info("Deleting channel with id: {}", id);
        channelRepository.deleteById(id);
    }
}
