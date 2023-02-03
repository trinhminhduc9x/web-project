package com.codegym.dating.controller;


import com.codegym.dating.payload.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityRestController_authenticateUser {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void authenticateUser_loginRequest_13() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/public/login")
                        .content(this.objectMapper.writeValueAsString(new LoginRequest()))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void authenticateUser_Username_13() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassword("123123");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/public/login")
                        .content(this.objectMapper.writeValueAsString(new LoginRequest()))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void authenticateUser_Password_13() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("huy@gmail.com");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/public/login")
                        .content(this.objectMapper.writeValueAsString(new LoginRequest()))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void authenticateUser_loginRequest_14() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("");
        loginRequest.setPassword("");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/public/login")
                        .content(this.objectMapper.writeValueAsString(loginRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void authenticateUser_Username_14() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("");
        loginRequest.setPassword("123123");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/public/login")
                        .content(this.objectMapper.writeValueAsString(loginRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void authenticateUser_Password_14() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("minhhuyyy@gmail.com");
        loginRequest.setPassword("");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/public/login")
                        .content(this.objectMapper.writeValueAsString(loginRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void authenticateUser_LoginRequest_16() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("minhhuyyy@gmail.com");
        loginRequest.setPassword("123321");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/public/login")
                        .content(this.objectMapper.writeValueAsString(loginRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void authenticateUser_LoginRequest_18() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("minhhuyyy@gmail.com");
        loginRequest.setPassword("123123");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/public/login")
                        .content(this.objectMapper.writeValueAsString(loginRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                //mỗi lần đăng nhập là tạo mới 1 token nên em không test token ở đây
                .andExpect(jsonPath("tokenType").value("Bearer"))
                .andExpect(jsonPath("idAccount").value(7))
                .andExpect(jsonPath("roles").value("ROLE_ADMIN"));
    }
}
