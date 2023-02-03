package com.codegym.dating.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class HobbitRestController_FindByIdUser {
    @Autowired
    private MockMvc mockMvc;

    @Test   //400
    public void findAllHobbitByIdUser_id_1() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get(
                                "/api/users/hobbits/{id}", "null"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test   //404
    public void findAllHobbitByIdUser_id_2() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get(
                                "/api/users/hobbits/{id}", ""))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }


    @Test   //404
    public void findAllHobbitByIdUser_id_3() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get(
                                "/api/users/hobbits/{id}", "25"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test  //200
    public void findAllHobbitByIdUser_id_4() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get(
                                "/api/users/hobbits/{id}", "2"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
        // Em không biết  cách lấy ra từng phần tử của list nên chỉ lấy đc status 200 thôi.

    }
}