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
public class ListFriendRestController_getListFriend {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getListFriend_7() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/list/friend_list/null"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getListFriend_8() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/list/friend_list/"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getListFriend_9() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/list/friend_list/100"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getListFriend_10() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/list/friend_list/20"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getListFriend_11() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/list/friend_list/1?page=1"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("totalPages").value(2))
                .andExpect(jsonPath("totalElements").value(4))
                .andExpect(jsonPath("content[0].idUser").value(5))
                .andExpect(jsonPath("content[0].name").value("Phạm Hữu Minh Tâm"))
                .andExpect(jsonPath("content[0].age").value("26"))
                .andExpect(jsonPath("content[0].avatar").value("chuacoanh"));
    }
}

