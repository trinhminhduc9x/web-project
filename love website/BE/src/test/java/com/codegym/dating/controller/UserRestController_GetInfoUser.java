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
public class UserRestController_GetInfoUser {
    @Autowired
    private MockMvc mockMvc;

    @Test   //404
    public void findUserById_id_1() throws Exception {
            this.mockMvc.perform(
                    MockMvcRequestBuilders.get(
                            "api/users/users/{id}", "null"))
                    .andDo(print())
                    .andExpect(status().is4xxClientError());
    }

    @Test   //404
    public void findUserById_id_2() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get(
                                "api/users/users/{id}", ""))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }


    @Test  // 404
    public void findUserById_id_4() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get(
                                "api/users/users/{id}", "25"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test  //200
    public void findUserById_id_3() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get(
                                "/api/users/users/{id}", "10"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("idUser").value(10))
                .andExpect(jsonPath("address").value("K123/45 Lê Lợi, Hồ Chí Minh"))
                .andExpect(jsonPath("avatar").value("chuacoanh"))
                .andExpect(jsonPath("coin").value(5773))
                .andExpect(jsonPath("dateOfBirth").value("1994-07-01"))
                .andExpect(jsonPath("gender").value(true))
                .andExpect(jsonPath("job").value("Nhân Viên Bán Hàng"))
                .andExpect(jsonPath("joinDay").value("2020-02-12"))
                .andExpect(jsonPath("married").value(true))
                .andExpect(jsonPath("name").value("Lê Hồng Phú"))
                .andExpect(jsonPath("idTypeUser").value(1));
    }
}
