package com.c0722g1repobe.controller;

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
public class PostRestController_showAndSearchListApproval {
    @Autowired
    private MockMvc mockMvc;
@Test
public void getListPost_6() throws Exception {
    this.mockMvc.perform(
                    MockMvcRequestBuilders.get("/api/posts?page=0"))
            .andDo(print())
            .andExpect(status().is2xxSuccessful())
            .andExpect(jsonPath("totalPages").value(2))
            .andExpect(jsonPath("totalElements").value(5))
            .andExpect(jsonPath("content[0].area").value(3000))
            .andExpect(jsonPath("content[0].landType").value("Nhà"))
            .andExpect(jsonPath("content[0].demandType").value("Mua"))
            .andExpect(jsonPath("content[0].note").value("abc"));
}
    @Test
    public void getListPost_5() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/posts?page=5"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}
