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
public class PostRestController_viewListPost {
    @Autowired
    private MockMvc mockMvc;

    /**
     * Function used to test the list of Posts with size = 0
     * Time: 02/01/2023
     * Author:DatTQ
     */
    @Test
    public void getListPost_5() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/post"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * Function used to test the list of Posts with size > 0.Success
     * Time: 02/01/2023
     * Author:DatTQ
     */
    @Test
    public void getListPost_6() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/post"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("content[0].idPost").value(1))
                .andExpect(jsonPath("content[0].price").value(new Double("5000000000")))
                .andExpect(jsonPath("content[0].dateCreation").value("2023-02-02"))
                .andExpect(jsonPath("content[0].statusPost").value(1))
                .andExpect(jsonPath("content[0].numberAddress").value("Lô 100A"))
                .andExpect(jsonPath("content[0].nameWards").value("Hương Xuân"))
                .andExpect(jsonPath("content[0].nameDistrict").value("Hương Trà"))
                .andExpect(jsonPath("content[0].nameCity").value("Huế"))
                .andExpect(jsonPath("content[0].yearPost").value("2023"))
                .andExpect(jsonPath("content[0].monthPost").value("02"))
                .andExpect(jsonPath("content[4].idPost").value(9))
                .andExpect(jsonPath("content[4].price").value(new Double("4000000000")))
                .andExpect(jsonPath("content[4].dateCreation").value("2023-01-01"))
                .andExpect(jsonPath("content[4].statusPost").value(1))
                .andExpect(jsonPath("content[4].numberAddress").value("123 Trần Hưng Đạo"))
                .andExpect(jsonPath("content[4].nameWards").value("Phú Hội"))
                .andExpect(jsonPath("content[4].nameDistrict").value("TP Huế"))
                .andExpect(jsonPath("content[4].nameCity").value("Huế"))
                .andExpect(jsonPath("content[4].yearPost").value("2023"))
                .andExpect(jsonPath("content[4].monthPost").value("01"));
    }
}
