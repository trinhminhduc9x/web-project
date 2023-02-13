package com.c0722g1repobe.controller;

import com.c0722g1repobe.dto.form.DataFormDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DataFormRestController_viewCreateDataForm {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Create by: KhanhLB
     * Date created: 01/02/2023
     * Function: this function createDataForm to test the validation of field urlDataForm more specific is empty
     */
    @Test
    public void createDataForm_urlDataForm_14() throws Exception {

        DataFormDto dataFormDto = new DataFormDto();
        dataFormDto.setContentDataForm("hợp đồng 6");
        dataFormDto.setUrlDataForm(" ");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/data-form/save")
                        .content(this.objectMapper.writeValueAsString(dataFormDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * Create by: KhanhLB
     * Date created: 01/02/2023
     * Function: this function createDataForm to test the validation of field contentDataForm more specific is empty
     */
    @Test
    public void createDataForm_contentDataForm_14() throws Exception {

        DataFormDto dataFormDto = new DataFormDto();
        dataFormDto.setContentDataForm(" ");
        dataFormDto.setUrlDataForm("aacacacac");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/data-form/save")
                        .content(this.objectMapper.writeValueAsString(dataFormDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    /**
     * Create by: KhanhLB
     * Date created: 01/02/2023
     * Function: All [items] are valid
     */
    @Test
    public void createDataForm_18() throws Exception {

        DataFormDto dataFormDto = new DataFormDto();
        dataFormDto.setContentDataForm("hợp đồng 7");
        dataFormDto.setUrlDataForm("ababab");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/data-form/save")
                        .content(this.objectMapper.writeValueAsString(dataFormDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }
    /**
     * Create by: KhanhLB
     * Date created: 01/02/2023
     * Function: this function createDataForm to test the validation of field contentDataForm more specific is null
     */
    @Test
    public void createDataForm_contentDataForm_13() throws Exception {
        DataFormDto dataFormDto = new DataFormDto();
        dataFormDto.setContentDataForm("");
        dataFormDto.setUrlDataForm("abacaav");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/data-form/save")
                        .content(this.objectMapper.writeValueAsString(dataFormDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    /**
     * Create by: KhanhLB
     * Date created: 01/02/2023
     * Function: this function createDataForm to test the validation of field urlDataForm more specific is null
     */
    @Test
    public void createDataForm_urlDataForm_13() throws Exception {
        DataFormDto dataFormDto = new DataFormDto();
        dataFormDto.setContentDataForm("hợp đồng 8");
        dataFormDto.setUrlDataForm("");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/data-form/save")
                        .content(this.objectMapper.writeValueAsString(dataFormDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    /**
     * Create by: KhanhLB
     * Date created: 01/02/2023
     * Function: this function createDataForm to test the validation of field contentDataForm more specific is containing special character
     */
    @Test
    public void createDataForm_contentDataForm_15() throws Exception {
        DataFormDto dataFormDto = new DataFormDto();
        dataFormDto.setContentDataForm("Hop dong #$%^");
        dataFormDto.setUrlDataForm("abcadwq");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/data-form/save")
                        .content(this.objectMapper.writeValueAsString(dataFormDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    /**
     * Create by: KhanhLB
     * Date created: 01/02/2023
     * Function: this function createDataForm to test the validation of field urlDataForm more specific is containing special character
     */
    @Test
    public void createDataForm_urlDataForm_15() throws Exception {
        DataFormDto dataFormDto = new DataFormDto();
        dataFormDto.setContentDataForm("hợp đồng 9");
        dataFormDto.setUrlDataForm("_+__+_+");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/data-form/save")
                        .content(this.objectMapper.writeValueAsString(dataFormDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    /**
     * Create by: KhanhLB
     * Date created: 01/02/2023
     * Function: this function createDataForm to test the validation of field contentDataForm more specific is min length
     */
    @Test
    public void createDataForm_contentDataForm_16() throws Exception {
        DataFormDto dataFormDto = new DataFormDto();
        dataFormDto.setContentDataForm("A");
        dataFormDto.setUrlDataForm("adadadadads");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/data-form/save")
                        .content(this.objectMapper.writeValueAsString(dataFormDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    /**
     * Create by: KhanhLB
     * Date created: 01/02/2023
     * Function: this function createDataForm to test the validation of field urlDataForm more specific is min length
     */
    @Test
    public void createDataForm_urlDataForm_16() throws Exception {
        DataFormDto dataFormDto = new DataFormDto();
        dataFormDto.setContentDataForm("hợp đồng 10");
        dataFormDto.setUrlDataForm("A");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/data-form/save")
                        .content(this.objectMapper.writeValueAsString(dataFormDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    /**
     * Create by: KhanhLB
     * Date created: 01/02/2023
     * Function: this function createDataForm to test the validation of field contentDataForm more specific is max length
     */

    @Test
    public void createDataForm_contentDataForm_17() throws Exception {
        DataFormDto dataFormDto = new DataFormDto();
        dataFormDto.setContentDataForm("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        dataFormDto.setUrlDataForm("avavavavavav");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/data-form/save")
                        .content(this.objectMapper.writeValueAsString(dataFormDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    /**
     * Create by: KhanhLB
     * Date created: 01/02/2023
     * Function: this function createDataForm to test the validation of field urlDataForm more specific is max length
     */
    @Test
    public void createDataForm_urlDataForm_17() throws Exception {
        DataFormDto dataFormDto = new DataFormDto();
        dataFormDto.setUrlDataForm("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        dataFormDto.setContentDataForm("avavavavavav");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/data-form/save")
                        .content(this.objectMapper.writeValueAsString(dataFormDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

}
