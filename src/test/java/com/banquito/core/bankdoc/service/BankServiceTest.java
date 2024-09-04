package com.banquito.core.bankdoc.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.banquito.core.bankdoc.model.Bank;
import com.banquito.core.bankdoc.repository.BankRepository;

@ExtendWith(MockitoExtension.class)
public class BankServiceTest {
    @Mock
    private BankRepository bankRepository;

    @InjectMocks
    private BankService bankService;

    private Bank bank;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        bank.setId("1");
        bank.setCode("BANK001");
        bank.setName("Test Bank");
        bank.setStartDate(new Date());
    }

    @Test
    void testGetAllBanks() {
        when(bankRepository.findAll()).thenReturn(Arrays.asList(bank));

        List<Bank> banks = bankService.getAllBanks();

        assertEquals(1, banks.size());
        assertEquals("Test Bank", banks.get(0).getName());

        verify(bankRepository, times(1)).findAll();
    }

    @Test
    void testGetBankById() {
        when(bankRepository.findById("1")).thenReturn(Optional.of(bank));

        Optional<Bank> result = bankService.getBankById("1");

        assertTrue(result.isPresent());
        assertEquals("Test Bank", result.get().getName());

        verify(bankRepository, times(1)).findById("1");
    }

    @Test
    void testCreateBank() {
        when(bankRepository.save(any(Bank.class))).thenReturn(bank);

        Bank createdBank = bankService.createBank(bank);

        assertEquals("Test Bank", createdBank.getName());

        verify(bankRepository, times(1)).save(bank);
    }

    @Test
    void testUpdateBank() {
        when(bankRepository.save(any(Bank.class))).thenReturn(bank);

        Bank updatedBank = bankService.updateBank(bank);

        assertEquals("Test Bank", updatedBank.getName());

        verify(bankRepository, times(1)).save(bank);
    }

    @Test
    void testDeleteBank() {
        bankService.deleteBank("1");

        verify(bankRepository, times(1)).deleteById("1");
    }
}
