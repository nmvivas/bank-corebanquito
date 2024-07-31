package com.banquito.core.bankdoc.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.bankdoc.dto.ChannelDTO;
import com.banquito.core.bankdoc.service.ChannelService;
import com.banquito.core.bankdoc.util.mapper.ChannelMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/channel")
@CrossOrigin(origins = "*")
@Tag(name = "Channel", description = "Endpoints for managing channels")
public class ChannelController {

    private final ChannelService channelService;
    private final ChannelMapper channelMapper;

    public ChannelController(ChannelService channelService, ChannelMapper channelMapper) {
        this.channelService = channelService;
        this.channelMapper = channelMapper;
    }

    @GetMapping
    @Operation(summary = "Get all channels", description = "Retrieve a list of all channels")
    public List<ChannelDTO> getAllChannels() {
        return channelService.findAll().stream().map(channelMapper::toChannelDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get channel by ID", description = "Retrieve a channel by its ID")
    public ChannelDTO getChannelById(@PathVariable String id) {
        return channelMapper.toChannelDTO(channelService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Create a channel", description = "Create a new channel")
    public ChannelDTO createChannel(@RequestBody ChannelDTO channelDTO) {
        return channelMapper.toChannelDTO(channelService.save(channelMapper.toChannel(channelDTO)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a channel", description = "Update an existing channel")
    public ChannelDTO updateChannel(@PathVariable String id, @RequestBody ChannelDTO channelDTO) {
        channelDTO.setId(id);
        return channelMapper.toChannelDTO(channelService.save(channelMapper.toChannel(channelDTO)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a channel", description = "Delete a channel by its ID")
    public void deleteChannel(@PathVariable String id) {
        channelService.deleteById(id);
    }
}
