package com.banquito.core.bankdoc.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.banquito.core.bankdoc.model.Bank;
import com.banquito.core.bankdoc.service.BankService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
class BankControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BankService bankService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllBanks() throws Exception {
        Bank bank = new Bank();
        bank.setId("1");
        bank.setCode("001");
        bank.setName("Test Bank");
        bank.setStartDate(new Date());
        
        List<Bank> banks = Arrays.asList(bank);
        when(bankService.getAllBanks()).thenReturn(banks);

        mockMvc.perform(get("/bank-microservice/api/v1/bank"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].code").value("001"))
                .andExpect(jsonPath("$[0].name").value("Test Bank"))
                .andDo(print());

        verify(bankService, times(1)).getAllBanks();
    }

    @Test
    void testGetBankById() throws Exception {
        Bank bank = new Bank();
        bank.setId("1");
        bank.setCode("001");
        bank.setName("Test Bank");
        bank.setStartDate(new Date());
        when(bankService.getBankById("1")).thenReturn(Optional.of(bank));

        mockMvc.perform(get("/bank-microservice/api/v1/bank/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.code").value("001"))
                .andExpect(jsonPath("$.name").value("Test Bank"))
                .andDo(print());

        verify(bankService, times(1)).getBankById("1");
    }

    @Test
    void testCreateBank() throws Exception {
        Bank bank = new Bank();
        bank.setId("1");
        bank.setCode("001");
        bank.setName("Test Bank");
        bank.setStartDate(new Date());
        when(bankService.createBank(any(Bank.class))).thenReturn(bank);

        mockMvc.perform(post("/bank-microservice/api/v1/bank")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(bank)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.code").value("001"))
                .andExpect(jsonPath("$.name").value("Test Bank"))
                .andDo(print());

        verify(bankService, times(1)).createBank(any(Bank.class));
    }

    @Test
    void testUpdateBank() throws Exception {
        Bank bank = new Bank();
        bank.setId("1");
        bank.setCode("001");
        bank.setName("Test Bank");
        bank.setStartDate(new Date());
        when(bankService.updateBank(any(Bank.class))).thenReturn(bank);

        mockMvc.perform(put("/bank-microservice/api/v1/bank")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(bank)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.code").value("001"))
                .andExpect(jsonPath("$.name").value("Test Bank"))
                .andDo(print());

        verify(bankService, times(1)).updateBank(any(Bank.class));
    }

    @Test
    void testDeleteBank() throws Exception {

        doNothing().when(bankService).deleteBank("1");
    

        mockMvc.perform(delete("/bank-microservice/api/v1/bank/1"))
                .andExpect(status().isOk());
    
        verify(bankService, times(1)).deleteBank("1");
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}