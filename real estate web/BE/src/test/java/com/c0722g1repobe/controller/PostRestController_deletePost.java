package com.c0722g1repobe.controller;


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
public class PostRestController_deletePost {

    @Autowired
    private MockMvc mockMvc;
    @Test
    public void approvalPost_id_25(){
        try {
            this.mockMvc.perform(
                            MockMvcRequestBuilders.delete("/api/posts/{id}", "null"))
                    .andDo(print())
                    .andExpect(status().is4xxClientError());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void approvalPost_id_26(){
        try {
            this.mockMvc.perform(
                            MockMvcRequestBuilders.delete("/api/posts/{id}", ""))
                    .andDo(print())
                    .andExpect(status().is4xxClientError());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void approvalPost_id_27(){
        try {
            this.mockMvc.perform(
                            MockMvcRequestBuilders.delete("/api/posts/{id}", 3))
                    .andDo(print())
                    .andExpect(status().is4xxClientError());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void approvalPost_id_28(){
        try {
            this.mockMvc.perform(
                            MockMvcRequestBuilders.delete("/api/posts/{id}", 1))
                    .andDo(print())
                    .andExpect(status().is2xxSuccessful());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
