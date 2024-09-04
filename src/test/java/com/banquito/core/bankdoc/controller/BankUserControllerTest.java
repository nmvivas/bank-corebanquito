package com.banquito.core.bankdoc.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.banquito.core.bankdoc.model.Bank;
import com.banquito.core.bankdoc.model.BankUser;
import com.banquito.core.bankdoc.service.BankService;
import com.banquito.core.bankdoc.service.BankUserService;
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

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
public class BankUserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private BankUserService bankUserService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testGetAllUsers() throws Exception {

        BankUser user1 = new BankUser();
        user1.setId("1");
        user1.setCodeBank("001");
        user1.setUniqueId("uniqueId1");
        user1.setUserName("testUser1");
        user1.setFirstName("John");
        user1.setLastName("Doe");
        user1.setFullName("John Doe");
        user1.setTypeUser("admin");
        user1.setPassword("password123");
        user1.setCreationDate(LocalDateTime.now());
        user1.setState("active");
        user1.setLastLogin(LocalDateTime.now());
        user1.setEmail("john.doe1@example.com");

        BankUser user2 = new BankUser();
        user2.setId("2");
        user2.setCodeBank("002");
        user2.setUniqueId("uniqueId2");
        user2.setUserName("testUser2");
        user2.setFirstName("Jane");
        user2.setLastName("Smith");
        user2.setFullName("Jane Smith");
        user2.setTypeUser("user");
        user2.setPassword("password456");
        user2.setCreationDate(LocalDateTime.now());
        user2.setState("inactive");
        user2.setLastLogin(LocalDateTime.now());
        user2.setEmail("jane.smith@example.com");

        when(bankUserService.getAllUsers()).thenReturn(List.of(user1, user2));

        mockMvc.perform(get("/bank-microservice/api/v1/bankuser")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].uniqueId").value(user1.getUniqueId()))
                .andExpect(jsonPath("$[1].uniqueId").value(user2.getUniqueId()))
                .andDo(print());
    }

    @Test
    void testGetUserByUniqueId() throws Exception {
        BankUser bankUser = new BankUser();
        bankUser.setId("1");
        bankUser.setUniqueId("UNIQUE001");
        bankUser.setUserName("testUser");
        bankUser.setFirstName("John");
        bankUser.setLastName("Doe");
        bankUser.setFullName("John Doe");
        bankUser.setTypeUser("ADMIN");
        bankUser.setPassword("password");
        bankUser.setCreationDate(LocalDateTime.now());
        bankUser.setState("ACTIVE");
        bankUser.setLastLogin(LocalDateTime.now());
        bankUser.setEmail("test@example.com");

        Optional<BankUser> user = Optional.of(bankUser);

        when(bankUserService.getUserByUniqueId("UNIQUE001")).thenReturn(user);

        mockMvc.perform(get("/bank-microservice/api/v1/bankuser/UNIQUE001"))
            .andDo(print()) 
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.uniqueId").value("UNIQUE001"));

        verify(bankUserService, times(1)).getUserByUniqueId("UNIQUE001");
    }

    @Test
    void testCreateUser() throws Exception {
        BankUser user = new BankUser();
        user.setId("1");
        user.setCodeBank("001");
        user.setUniqueId("uniqueId1");
        user.setUserName("testUser");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setFullName("John Doe");
        user.setTypeUser("admin");
        user.setPassword("password123");
        user.setCreationDate(LocalDateTime.now());
        user.setState("active");
        user.setLastLogin(LocalDateTime.now());
        user.setEmail("john.doe@example.com");

        when(bankUserService.createUser(any(BankUser.class))).thenReturn(user);

        mockMvc.perform(post("/bank-microservice/api/v1/bankuser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uniqueId").value(user.getUniqueId()))
                .andDo(print());
    }

    @Test
    void testUpdateUser() throws Exception {
        BankUser user = new BankUser();
        user.setId("1");
        user.setCodeBank("001");
        user.setUniqueId("uniqueId1");
        user.setUserName("testUser");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setFullName("John Doe");
        user.setTypeUser("admin");
        user.setPassword("password123");
        user.setCreationDate(LocalDateTime.now());
        user.setState("active");
        user.setLastLogin(LocalDateTime.now());
        user.setEmail("john.doe@example.com");

        when(bankUserService.updateUser(any(BankUser.class))).thenReturn(user);

        mockMvc.perform(put("/bank-microservice/api/v1/bankuser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uniqueId").value(user.getUniqueId()))
                .andDo(print());
    }

    @Test
    void testDeleteUserByUniqueId() throws Exception {

        doNothing().when(bankUserService).deleteUserByUniqueId(anyString());

        mockMvc.perform(delete("/bank-microservice/api/v1/bankuser/{uniqueId}", "uniqueId1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void testGetUserByEmail() throws Exception {
        BankUser user = new BankUser();
        user.setId("1");
        user.setCodeBank("001");
        user.setUniqueId("uniqueId1");
        user.setUserName("testUser");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setFullName("John Doe");
        user.setTypeUser("admin");
        user.setPassword("password123");
        user.setCreationDate(LocalDateTime.now());
        user.setState("active");
        user.setLastLogin(LocalDateTime.now());
        user.setEmail("john.doe@example.com");

        when(bankUserService.getUserByEmail(anyString())).thenReturn(Optional.of(user));

        mockMvc.perform(get("/bank-microservice/api/v1/bankuser/email/{email}", "john.doe@example.com")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uniqueId").value(user.getUniqueId()))
                .andDo(print());
    }

    @Test
    void testGetUserByUserName() throws Exception {
        BankUser user = new BankUser();
        user.setId("1");
        user.setCodeBank("001");
        user.setUniqueId("uniqueId1");
        user.setUserName("testUser");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setFullName("John Doe");
        user.setTypeUser("admin");
        user.setPassword("password123");
        user.setCreationDate(LocalDateTime.now());
        user.setState("active");
        user.setLastLogin(LocalDateTime.now());
        user.setEmail("john.doe@example.com");

        when(bankUserService.getUserByUserName(anyString())).thenReturn(Optional.of(user));

        mockMvc.perform(get("/bank-microservice/api/v1/bankuser/username/{userName}", "testUser")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uniqueId").value(user.getUniqueId()))
                .andDo(print());
    }

    @Test
    void testGetUserByTypeUser() throws Exception {
        BankUser user = new BankUser();
        user.setId("1");
        user.setCodeBank("001");
        user.setUniqueId("uniqueId1");
        user.setUserName("testUser");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setFullName("John Doe");
        user.setTypeUser("admin");
        user.setPassword("password123");
        user.setCreationDate(LocalDateTime.now());
        user.setState("active");
        user.setLastLogin(LocalDateTime.now());
        user.setEmail("john.doe@example.com");
    
        when(bankUserService.getUserByTypeUser("admin")).thenReturn(Optional.of(user));
    
        mockMvc.perform(get("/bank-microservice/api/v1/bankuser/typeuser/admin")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uniqueId").value(user.getUniqueId()))
                .andDo(print());
    }

    @Test
    void testGetUserByFullName() throws Exception {
        BankUser user = new BankUser();
        user.setId("1");
        user.setCodeBank("001");
        user.setUniqueId("uniqueId1");
        user.setUserName("testUser");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setFullName("John Doe");
        user.setTypeUser("admin");
        user.setPassword("password123");
        user.setCreationDate(LocalDateTime.now());
        user.setState("active");
        user.setLastLogin(LocalDateTime.now());
        user.setEmail("john.doe@example.com");

        when(bankUserService.getUserByFullName(anyString())).thenReturn(Optional.of(user));

        mockMvc.perform(get("/bank-microservice/api/v1/bankuser/fullname/{fullName}", "John Doe")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uniqueId").value(user.getUniqueId()))
                .andDo(print());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
