package com.c0722g1repobe.controller;

import com.c0722g1repobe.entity.account.Account;
import com.c0722g1repobe.entity.account.Role;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountRestController_updatePassword {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void updatePassword_encryptPassword_19() throws Exception {

        Account account = new Account();
        account.setEncryptPassword("");
        account.setUsernameAccount("Nguyễn Anhh");
        account.setEmail("a@gmail.com");
        account.setName("hi");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/api/account/update-customer", "null")
                        .content(this.objectMapper.writeValueAsString(account))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void updatePassword_encryptPassword_24() throws Exception {

        Account account = new Account();
        account.setEncryptPassword("12324");
        account.setUsernameAccount("Nguyễn Anhh");
        account.setEmail("a@gmail.com");
        account.setName("hi");

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/api/account/update-customer", "12324")
                        .content(this.objectMapper.writeValueAsString(account))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}
