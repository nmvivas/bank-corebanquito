package com.banquito.core.bankdoc.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ObjectFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.banquito.core.bankdoc.dto.BankUserDTO;
import com.banquito.core.bankdoc.model.BankUser;

@Mapper(componentModel = "spring", uses = { BankUserMapper.BankUserFactory.class })
public interface BankUserMapper {

    BankUserDTO toBankUserDTO(BankUser bankUser);

    BankUser toBankUser(BankUserDTO bankUserDTO);

    @Component
    class BankUserFactory {
        private static final Logger logger = LoggerFactory.getLogger(BankUserFactory.class);

        @ObjectFactory
        public BankUser create(BankUserDTO dto) {
            BankUser user = new BankUser(dto.getId(), dto.getCodeBank(), dto.getUniqueId(),
                    dto.getUserName(), dto.getFirstName(), dto.getLastName(), dto.getFullName(), dto.getTypeUser(),
                    dto.getPassword(), dto.getCreationDate(), dto.getState(), dto.getLastLogin(),
                    dto.getEmail());
            logger.debug("Created BankUser from DTO: {}", user);
            return user;
        }
    }
}
