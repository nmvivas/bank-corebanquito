package com.banquito.core.bankdoc.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.bankdoc.model.Channel;
import com.banquito.core.bankdoc.service.ChannelService;

@RestController
@RequestMapping("/api/channel")
public class ChannelController {

    private final ChannelService channelService;

    public ChannelController(ChannelService channelService) {
        this.channelService = channelService;
    }

    @GetMapping
    public List<Channel> getAllChannels() {
        return channelService.findAll();
    }

    @GetMapping("/{id}")
    public Channel getChannelById(@PathVariable String id) {
        return channelService.findById(id);
    }

    @PostMapping
    public Channel createChannel(@RequestBody Channel channel) {
        return channelService.save(channel);
    }

    @PutMapping("/{id}")
    public Channel updateChannel(@PathVariable String id, @RequestBody Channel channel) {
        channel.setId(id);
        return channelService.save(channel);
    }

    @DeleteMapping("/{id}")
    public void deleteChannel(@PathVariable String id) {
        channelService.deleteById(id);
    }
}