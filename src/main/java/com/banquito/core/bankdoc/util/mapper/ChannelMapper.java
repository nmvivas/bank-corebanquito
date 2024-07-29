package com.banquito.core.bankdoc.util.mapper;

import org.mapstruct.Mapper;

import com.banquito.core.bankdoc.dto.ChannelDTO;
import com.banquito.core.bankdoc.model.Channel;

@Mapper(componentModel = "spring")
public interface ChannelMapper {
    ChannelDTO toChannelDTO(Channel channel);

    Channel toChannel(ChannelDTO channelDTO);
}
