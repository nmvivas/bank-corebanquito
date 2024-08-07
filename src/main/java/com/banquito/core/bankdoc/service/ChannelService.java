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

    public List<Channel> obtenerTodosLosCanales() {
        return channelRepository.findAll();
    }

    public Optional<Channel> obtenerCanalPorId(String id) {
        return channelRepository.findById(id);
    }

    public Channel crearCanal(Channel channel) {
        return channelRepository.save(channel);
    }

    public Channel actualizarCanal(Channel channel) {
        return channelRepository.save(channel);
    }

    public void eliminarCanal(String id) {
        channelRepository.deleteById(id);
    }
}
