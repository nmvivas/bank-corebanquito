package com.banquito.core.bankdoc.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.banquito.core.bankdoc.model.Channel;
import com.banquito.core.bankdoc.repository.ChannelRepository;

@ExtendWith(MockitoExtension.class)
public class ChannelServiceTest {
    @Mock
    private ChannelRepository channelRepository;

    @InjectMocks
    private ChannelService channelService;

    private Channel channel;

    @BeforeEach
    void setUp() {
        channel = new Channel();
        channel.setId("1");
        channel.setCode("CH001");
        channel.setCodeBank("BANK001");
        channel.setName("Main Channel");
    }

    @Test
    void testGetAllChannels() {
        when(channelRepository.findAll()).thenReturn(Arrays.asList(channel));

        List<Channel> channels = channelService.getAllChannels();

        assertEquals(1, channels.size());
        assertEquals("CH001", channels.get(0).getCode());

        verify(channelRepository, times(1)).findAll();
    }

    @Test
    void testGetChannelById() {
        when(channelRepository.findById("1")).thenReturn(Optional.of(channel));

        Optional<Channel> result = channelService.getChannelById("1");

        assertTrue(result.isPresent());
        assertEquals("CH001", result.get().getCode());

        verify(channelRepository, times(1)).findById("1");
    }

    @Test
    void testCreateChannel() {
        when(channelRepository.save(any(Channel.class))).thenReturn(channel);

        Channel newChannel = new Channel();
        newChannel.setCode("CH002");
        newChannel.setCodeBank("BANK002");
        newChannel.setName("Secondary Channel");

        Channel createdChannel = channelService.createChannel(newChannel);

        assertEquals("CH001", createdChannel.getCode());  
        assertEquals("BANK001", createdChannel.getCodeBank());
        assertEquals("Main Channel", createdChannel.getName());

        verify(channelRepository, times(1)).save(any(Channel.class));
    }

    @Test
    void testUpdateChannel() {
        when(channelRepository.save(any(Channel.class))).thenReturn(channel);

        channel.setName("Updated Channel");
        Channel updatedChannel = channelService.updateChannel(channel);

        assertEquals("Updated Channel", updatedChannel.getName());

        verify(channelRepository, times(1)).save(channel);
    }

    @Test
    void testDeleteChannel() {
        channelService.deleteChannel("1");

        verify(channelRepository, times(1)).deleteById("1");
    }
}
