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
public class DataFormRestController_viewListDataForm {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getListDataForm_5() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/dataForm?size=null"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getListDataForm_6() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/data-form?page=0"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("totalPages").value(1))
                .andExpect(jsonPath("totalElements").value(5))
                .andExpect(jsonPath("dataForm[0].contentDataForm").value("hợp đồng 1"))
                .andExpect(jsonPath("dataForm[0].urlDataForm").value("aaaaaaa"))
                .andExpect(jsonPath("dataForm[4].contentDataForm").value("hợp đồng 5"))
                .andExpect(jsonPath("dataForm[4].urlDataForm").value("aaaacccc"));
    }

    @Test
    public void getListDataForm_7() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/data-form?contentDataForm=null"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getListDataForm_8() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/data-form?contentDataForm="))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void getListDataForm_9() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/data-form?contentDataForm=123"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getListDataForm_10() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/data-form?contentDataForm=hợp"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getSearchListPost_year_and_month_11() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/data-form?contentDataForm=hợp đồng 1"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("dataForm[0].contentDataForm").value("hợp đồng 1"))
                .andExpect(jsonPath("dataForm[0].urlDataForm").value("aaaaaaa"));

    }
}