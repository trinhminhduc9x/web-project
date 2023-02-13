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
public class CustomerRestController_getCustomerById {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getCustomerById_idCustomer_1() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/customers/{idCustomer}", "null"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getCustomerById_idCustomer_4() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/customers/{idCustomer}", "1"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("idCustomer").value(1))
                .andExpect(jsonPath("nameCustomer").value("Nguyễn Hoàng Anh"))
                .andExpect(jsonPath("idCardCustomer").value("1920121212"));
    }
}
