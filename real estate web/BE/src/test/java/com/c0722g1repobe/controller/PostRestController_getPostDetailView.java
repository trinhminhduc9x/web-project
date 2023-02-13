package com.c0722g1repobe.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PostRestController_getPostDetailView {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getPostDetail_id_1() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/api/post/detail?id=null")
        ).andDo(print()).andExpect(status().is4xxClientError());
    }

    @Test
    public void getPostDetail_id_2() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/api/post/detail?id=")
        ).andDo(print()).andExpect(status().is4xxClientError());
    }

    @Test
    public void getPostDetail_id_3() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/api/post/detail?id=25")
        ).andDo(print()).andExpect(status().is4xxClientError());
    }

    @Test
    public void getPostDetail_id_4() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/post/detail?id=1")
                ).andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("namePost").value("Nhà cho thuê"))
                .andExpect(jsonPath("area").value(70))
                .andExpect(jsonPath("note").value("Nhà cho thuê khu vực Cẩm Lệ"))
                .andExpect(jsonPath("price").value("26 Triệu/Tháng"))
                .andExpect(jsonPath("direction").value("Bắc"))
                .andExpect(jsonPath("address").value("30 Nguyễn Phước Lan, Quận Cẩm Lệ, Thành phố Đà Nẵng"))
                .andExpect(jsonPath("demandType").value("Cho thuê"))
                .andExpect(jsonPath("landType").value("Nhà"));
    }
}
