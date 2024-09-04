package com.banquito.core.bankdoc.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.banquito.core.bankdoc.model.Channel;
import com.banquito.core.bankdoc.service.ChannelService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ChannelControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChannelService channelService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllChannels() throws Exception {
        Channel channel1 = new Channel();
        channel1.setId("1");
        channel1.setCode("CH01");
        channel1.setCodeBank("BANK01");
        channel1.setName("Channel 1");

        Channel channel2 = new Channel();
        channel2.setId("2");
        channel2.setCode("CH02");
        channel2.setCodeBank("BANK02");
        channel2.setName("Channel 2");

        List<Channel> channels = Arrays.asList(channel1, channel2);

        when(channelService.getAllChannels()).thenReturn(channels);

        mockMvc.perform(get("/bank-microservice/api/v1/channel")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[1].id").value("2"));

        verify(channelService, times(2)).getAllChannels();
    }

    @Test
    void testGetChannelById() throws Exception {
        Channel channel = new Channel();
        channel.setId("1");
        channel.setCode("CH01");
        channel.setCodeBank("BANK01");
        channel.setName("Channel 1");

        when(channelService.getChannelById("1")).thenReturn(Optional.of(channel));

        mockMvc.perform(get("/bank-microservice/api/v1/channel/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.code").value("CH01"));

        verify(channelService, times(1)).getChannelById("1");
    }

    @Test
    void testCreateChannel() throws Exception {
        Channel channel = new Channel();
        channel.setId("1");
        channel.setCode("CH01");
        channel.setCodeBank("BANK01");
        channel.setName("Channel 1");

        when(channelService.createChannel(any(Channel.class))).thenReturn(channel);

        String json = "{ \"id\": \"1\", \"code\": \"CH01\", \"codeBank\": \"BANK01\", \"name\": \"Channel 1\" }";

        mockMvc.perform(post("/bank-microservice/api/v1/channel")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.code").value("CH01"));

        verify(channelService, times(1)).createChannel(any(Channel.class));
    }

    @Test
    void testUpdateChannel() throws Exception {
        Channel channel = new Channel();
        channel.setId("1");
        channel.setCode("CH01");
        channel.setCodeBank("BANK01");
        channel.setName("Channel 1");

        when(channelService.updateChannel(any(Channel.class))).thenReturn(channel);

        String json = "{ \"id\": \"1\", \"code\": \"CH01\", \"codeBank\": \"BANK01\", \"name\": \"Channel 1\" }";

        mockMvc.perform(put("/bank-microservice/api/v1/channel")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.code").value("CH01"));

        verify(channelService, times(1)).updateChannel(any(Channel.class));
    }

    @Test
    void testDeleteChannel() throws Exception {
        doNothing().when(channelService).deleteChannel("1");

        mockMvc.perform(delete("/bank-microservice/api/v1/channel/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(channelService, times(1)).deleteChannel("1");
    }
}
