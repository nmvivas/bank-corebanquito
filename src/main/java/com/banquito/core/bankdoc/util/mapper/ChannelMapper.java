package com.banquito.core.bankdoc.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ObjectFactory;
import org.springframework.stereotype.Component;

import com.banquito.core.bankdoc.dto.ChannelDTO;
import com.banquito.core.bankdoc.model.Channel;

@Mapper(componentModel = "spring", uses = { ChannelMapper.ChannelFactory.class })
public interface ChannelMapper {
    ChannelDTO toChannelDTO(Channel channel);

    Channel toChannel(ChannelDTO channelDTO);

    @Component
    class ChannelFactory {
        @ObjectFactory
        public Channel create(ChannelDTO dto) {
            return new Channel(dto.getId(), dto.getCode(), dto.getCodeBank(), dto.getNameBank(), dto.getName());
        }
    }
}
