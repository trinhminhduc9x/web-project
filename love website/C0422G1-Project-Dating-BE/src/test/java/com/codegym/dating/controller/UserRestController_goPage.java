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
public class UserRestController_goPage {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void getPage() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/public/pageSearch/a"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getPage_search_7() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders.get(
                                "/api/public/pageSearch/?name="+ "null"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }


    @Test
    public void getPage_search_8() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders.get(
                                "/api/public/pageSearch/?name=" +"@$$#"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getPage_search_9() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders.get(
                                "/api/public/pageSearch/?name=" +"tradsdfasffgfggggggggggggggggggggggggggggggggasawertwetttttttttttttttttttttttt"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }


    @Test
    public void getPage_search_10() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders.get(
                                "/api/public/pageSearch/?name=" +"tt"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    @Test
    public void getPage_page_11() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/public/pageSearch?page=1"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("totalPages").value(4))
                .andExpect(jsonPath("totalElements").value(20))
                .andExpect(jsonPath("content[4].name").value("Lê Hồng Phú"))
                .andExpect(jsonPath("content[4].gender").value("true"))
                .andExpect(jsonPath("content[4].address").value("K123/45 Lê Lợi, Hồ Chí Minh"))
                .andExpect(jsonPath("content[4].coin").value("5773"))
                .andExpect(jsonPath("content[4].avatar").value("chuacoanh"))
                .andExpect(jsonPath("content[4].job").value("Nhân Viên Bán Hàng"));
    }
  @Test
    public void getPage_search_11() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/public/pageSearch?search=" +"a"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("totalPages").value(4))
                .andExpect(jsonPath("totalElements").value(20))
                .andExpect(jsonPath("content[1].name").value("Trần Thào Nhiên"))
                .andExpect(jsonPath("content[1].gender").value("false"))
                .andExpect(jsonPath("content[1].address").value("43 Trương Hán Siêu , Phường An Hài Bắc , Quận Sơn Trà , Thành Phố Đà Nẵng"))
                .andExpect(jsonPath("content[1].coin").value("3453"))
                .andExpect(jsonPath("content[1].avatar").value("chuacoanh"))
                .andExpect(jsonPath("content[1].job").value("Nhân Viên Văn Phòng"));
    }

}
