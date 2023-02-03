package com.codegym.dating.controller;

import org.junit.jupiter.api.Test;
import org.omg.CORBA.BAD_TYPECODE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserRestController_search {
    @Autowired
    private MockMvc mockMvc;

    // trường hợp không chọn bất kỳ 1 trường nào , vì theo srs được phép rỗng nên em vẫn để 2xx
    @Test
    public void userPage_5() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/users"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }


    @Test
    public void userPage_6() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/users?page=0"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("totalPages").value(1))
                .andExpect(jsonPath("totalElements").value(20))
                .andExpect(jsonPath("content[19].idUser").value(20))
                .andExpect(jsonPath("content[19].address").value("37 Yên Thế, Đà Nẵng"));

    }

    // trường hợp null
    @Test
    public void userPage_7_byName() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/users?name=null"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    // trường hợp rỗng
    @Test
    public void userPage_8_byName() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/users?name="))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

    }

    // Có dữ liệu
    @Test
    public void userPage_11_byName() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/users?name=T"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    // field address trường hợp null
    @Test
    public void userPage_7_byAddress() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/users?address=null")
        ).andDo(print()).andExpect(status().is(204));
    }

    // trường hợp rỗng
    @Test
    public void userPage_8_byAddress() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/users?address=")
        ).andDo(print()).andExpect(status().is2xxSuccessful());
    }

    // không có dữ liệu trong DB
    @Test
    public void userPage_9_byAddress() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/users?address=f")
        ).andDo(print()).andExpect(status().is(204));
    }

    // có dữ liệu trong DB
    @Test
    public void userPage_11_byAddress() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/users?address=N")
        ).andDo(print()).andExpect(status().is2xxSuccessful());
    }
    // ko có dữ liệu trong DB
    @Test
    public void userPage_9_byDateOfBirth() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/users?dateOfBirth=2007")
        ).andDo(print()).andExpect(status().is(204));
    }
    // field dateOfBirth trường hợp có dự liệu
    @Test
    public void userPage_11_byDateOfBirth() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/users?dateOfBirth=1970")
        ).andDo(print()).andExpect(status().is2xxSuccessful());
    }

    @Test
    public void userPage_7_byJob() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/users?job=null")

        ).andDo(print()).andExpect(status().is(204));
    }

    @Test
    public void userPage_8_byJob() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/users?job=")
        ).andDo(print()).andExpect(status().is2xxSuccessful());
    }

    @Test
    public void userPage_9_byJob() throws Exception{
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/api/users?job=f")
        ).andDo(print()).andExpect(status().is(204));
    }

    @Test
    public void userPage_11_byJob() throws Exception{
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/api/users?job=b")
        ).andDo(print()).andExpect(status().is2xxSuccessful());
    }

    @Test
    public void userPage_7_byGender() throws Exception{
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/api/users?gender=null")
        ).andDo(print()).andExpect(status().is(204));
    }

    @Test
    public void userPage_8_byGender() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/api/users?gender=")
        ).andDo(print()).andExpect(status().is2xxSuccessful());
    }

    @Test
    public void userPage_9_byGender()throws Exception{
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/api/users?gender=a")
        ).andDo(print()).andExpect(status().is(204));
    }

    @Test
    public void userPage_11_byGender()throws Exception{
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/api/users?gender=1")
        ).andDo(print()).andExpect(status().is2xxSuccessful());
    }

    @Test
    public void userPage_7_byHobbit() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/api/users?hobbit=null")
        ).andDo(print()).andExpect(status().is(204));
    }
}
