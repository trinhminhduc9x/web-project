package com.codegym.dating.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FriendListRestController_CheckFriend {
    @Autowired
    private MockMvc mockMvc;

    @Test   //404
    public void  checkFriend_idUser1_1() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get(
                        "/api/users/friendList/check/{idUser1}/{idUser2}","","1"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test   //404
    public void  checkFriend_idUser1_2() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get(
                                "/api/users/friendList/check/{idUser1}/{idUser2}",null,"1"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test   //404
    public void  checkFriend_idUser1_3() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get(
                                "/api/users/friendList/check/{idUser1}/{idUser2}","cđcs","1"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test   //200
    public void  checkFriend_idUser1_4() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get(
                                "/api/users/friendList/check/{idUser1}/{idUser2}","2","1"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }
    @Test   //404
    public void  checkFriend_idUser2_1() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get(
                                "/api/users/friendList/check/{idUser1}/{idUser2}","","1"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test   //404
    public void  checkFriend_idUser2_2() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get(
                                "/api/users/friendList/check/{idUser1}/{idUser2}",null,"1"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test   //404
    public void  checkFriend_idUser2_3() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get(
                                "/api/users/friendList/check/{idUser1}/{idUser2}","cđcs","1"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test   //200
    public void  checkFriend_idUser2_4() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get(
                                "/api/users/friendList/check/{idUser1}/{idUser2}","2","1"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }


}
