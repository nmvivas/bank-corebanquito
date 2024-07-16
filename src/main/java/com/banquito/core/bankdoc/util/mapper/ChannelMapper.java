package com.banquito.core.bankdoc.util.mapper;

import com.banquito.core.bankdoc.dto.ChannelDTO;
import com.banquito.core.bankdoc.model.Channel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ChannelMapper {
    ChannelMapper INSTANCE = Mappers.getMapper(ChannelMapper.class);

    ChannelDTO toChannelDTO(Channel channel);

    Channel toChannel(ChannelDTO channelDTO);
}
