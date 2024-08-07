package com.banquito.core.bankdoc.controller;

import java.util.List;
import java.util.Optional;

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
@RequestMapping("/bank-microservice/api/v1/channel")
@CrossOrigin(origins = "*")
@Tag(name = "Channel", description = "Endpoints for managing channels")
public class ChannelController {


    private final ChannelService channelService;

    public ChannelController(ChannelService channelService) {
        this.channelService = channelService;
    }

    @GetMapping
    @Operation(summary = "Get All Channels", description = "Retrieve a list of all channels")
    public List<Channel> getAllChannels() {
        return channelService.getAllChannels();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Channel by ID", description = "Retrieve a channel by its ID")
    public Optional<Channel> getChannelById(@PathVariable String id) {
        return channelService.getChannelById(id);
    }

    @PostMapping
    @Operation(summary = "Create Channel", description = "Create a new channel")
    public Channel createChannel(@RequestBody Channel channel) {
        return channelService.createChannel(channel);
    }

    @PutMapping
    @Operation(summary = "Update Channel", description = "Update an existing channel")
    public Channel updateChannel(@RequestBody Channel channel) {
        return channelService.updateChannel(channel);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Channel", description = "Delete a channel by its ID")
    public void deleteChannel(@PathVariable String id) {
        channelService.deleteChannel(id);
    }
}
