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
public class CustomerController_getInfoCustomer {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getInfoCustomer_id_1() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/customer/detail/{id}", "null"))
                .andDo(print())
                .andExpect(status().is4xxClientError());

    }

    @Test
    public void getInfoCustomer_id_3() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/customer/detail/{id}", "2"))
                .andDo(print())
                .andExpect(status().is4xxClientError());

    }

    @Test
    public void getInfoCustomer_4() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/customer/detail/{id}", "1"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("idCustomer").value(1))
                .andExpect(jsonPath("nameCustomer").value("Nguyễn Văn Huy"))
                .andExpect(jsonPath("emailCustomer").value("maohuy98798@gmail.com"));
    }
}
