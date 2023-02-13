package com.c0722g1repobe.controller;

import com.c0722g1repobe.dto.post.PostListViewDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PostRestController_getAllPost {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getListStudent_area_price_demandType_direction_city_5() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/post?area="+null+"&price="+null+"&demandType="+null+"&direction="+null+"&city="+null))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void getListStudent_area_price_demandType_direction_city_99() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/post?area= &price= &demandType= &direction= &city= "))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }
    @Test
    public void getListStudent_area_price_demandType_direction_city_9() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/post?area=11&price=11&demandType=11&direction=11&city=11"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void getListStudent_area_price_demandType_direction_city_10() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/post?area=50-100&price=1000000000-1500000000&demandType=dat&direction=dong&city=hanoi"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void getListStudent_area_price_demandType_direction_city_11() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/post?area=50-100&price=1000000000-1500000000&demandType=dat&direction=bac&city=hochiminh"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }
}
