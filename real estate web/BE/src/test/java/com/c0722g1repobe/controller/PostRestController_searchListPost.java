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
public class PostRestController_searchListPost {
    @Autowired
    private MockMvc mockMvc;
    /**
     * Function used to test the search list of Posts with parameter = null
     * Time: 01/02/2023
     * Author:DatTQ
     */
    @Test
    public void getSearchListPost_year_and_month_7() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/post/search?year=null&month=null"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    /**
     * Function used to test the search list of Posts with parameter = ""
     * Time: 01/02/2023
     * Author:DatTQ
     */
    @Test
    public void getSearchListPost_year_and_month_8() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/post/search?year=&month="))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * Function used to test the search list of Posts with parameter does not exist in DB
     * Time: 01/02/2023
     * Author:DatTQ
     */
    @Test
    public void getSearchListPost_year_and_month_9() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/post/search?year=a&month=b"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    /**
     * Function used to test the search list of Posts with parameter does exist in DB, list has size = 0
     * Time: 01/02/2023
     * Author:DatTQ
     */
    @Test
    public void getSearchListPost_year_and_month_10() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/post/search?year=2023&month=7"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * Function used to test the search list of Posts with parameter does exist in DB, list has size > 0
     * Time: 01/02/2023
     * Author:DatTQ
     */
    @Test
    public void getSearchListPost_year_and_month_11() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/post/search?year=2023&month=2"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("content[0].idPost").value(6))
                .andExpect(jsonPath("content[0].price").value(new Double("5000000000")))
                .andExpect(jsonPath("content[0].dateCreation").value("2023-02-02"))
                .andExpect(jsonPath("content[0].statusPost").value(1))
                .andExpect(jsonPath("content[0].numberAddress").value("Lô 100A"))
                .andExpect(jsonPath("content[0].nameWards").value("Hương Xuân"))
                .andExpect(jsonPath("content[0].nameDistrict").value("Hương Trà"))
                .andExpect(jsonPath("content[0].nameCity").value("Huế"))
                .andExpect(jsonPath("content[0].yearPost").value("2023"))
                .andExpect(jsonPath("content[0].monthPost").value("02"));
    }

}
