package com.banquito.core.bankdoc.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ObjectFactory;
import org.springframework.stereotype.Component;

import com.banquito.core.bankdoc.dto.BankUserDTO;
import com.banquito.core.bankdoc.model.BankUser;

@Mapper(componentModel = "spring", uses = { BankUserMapper.BankUserFactory.class })
public interface BankUserMapper {
    BankUserDTO toBankUserDTO(BankUser bankUser);

    BankUser toBankUser(BankUserDTO bankUserDTO);

    @Component
    class BankUserFactory {
        @ObjectFactory
        public BankUser create(BankUserDTO dto) {
            return new BankUser(dto.getId(), dto.getCodeBank(), dto.getUniqueId(),
                    dto.getUserName(), dto.getFirstName(), dto.getLastName(), dto.getFullName(), dto.getTypeUser(),
                    dto.getPassword(), dto.getCreationDate(), dto.getState(), dto.getLastLogin(),
                    dto.getEmail());
        }
    }
}
