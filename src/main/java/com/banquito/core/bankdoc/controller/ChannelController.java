package com.banquito.core.bankdoc.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/channel")
@CrossOrigin(origins = "*")
@Tag(name = "Channel", description = "Endpoints for managing channels")
public class ChannelController {

    private final ChannelService channelService;

    public ChannelController(ChannelService channelService) {
        this.channelService = channelService;
    }

    @GetMapping
    @Operation(summary = "Get all channels", description = "Retrieve a list of all channels")
    public List<Channel> getAllChannels() {
        return channelService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get channel by ID", description = "Retrieve a channel by its ID")
    public Channel getChannelById(@PathVariable String id) {
        return channelService.findById(id);
    }

    @PostMapping
    @Operation(summary = "Create a channel", description = "Create a new channel")
    public Channel createChannel(@RequestBody Channel channel) {
        return channelService.save(channel);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a channel", description = "Update an existing channel")
    public Channel updateChannel(@PathVariable String id, @RequestBody Channel channel) {
        channel.setId(id);
        return channelService.save(channel);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a channel", description = "Delete a channel by its ID")
    public void deleteChannel(@PathVariable String id) {
        channelService.deleteById(id);
    }
}
