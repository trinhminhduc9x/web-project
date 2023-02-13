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
public class DataFormRestController_updateDataForm {
    @Autowired
    private MockMvc mockMvc ;
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Create by: DungND,
     * Date created: 01/02/2023
     * Function: update dataForm item id == null
     *
     * @return error status code NOT FOUND
     */

    @Test
    public void updateDataForm_CodeDataForm_19() throws Exception {
        DataFormDto dataFormDto = new DataFormDto();
        dataFormDto.setContentDataForm("Hợp đồng kiểm thử");

        this.mockMvc.perform(MockMvcRequestBuilders
                        .patch("/api/form/update/{id}")
                        .content(this.objectMapper.writeValueAsString(dataFormDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * Create by: DungND,
     * Date created: 01/02/2023
     * Function: update dataForm item id == null
     *
     * @return error status code NOT FOUND
     */

    @Test
    public void updateDataForm_CodeDataForm_20() throws Exception {
        DataFormDto dataFormDto = new DataFormDto();
        dataFormDto.setCodeDataForm("");
        dataFormDto.setContentDataForm("Hợp đồng kiểm thử");

        this.mockMvc.perform(MockMvcRequestBuilders
                        .patch("/api/form/update/{id}")
                        .content(this.objectMapper.writeValueAsString(dataFormDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }


    /**
     * Create by: DungND
     * Date created: 01/02/2023
     * Function: elete dataForm with item id valid (exists in dabatabase)
     *
     * @return status code OK
     */

    @Test
    public void updateDataForm_CodeDataForm_24() throws Exception {
        DataFormDto dataFormDto = new DataFormDto();
        dataFormDto.setCodeDataForm("HS114");
        dataFormDto.setContentDataForm("Hợp đồng kiểm thử");
        this.mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/form/update/{id}")
                        .content(this.objectMapper.writeValueAsString(dataFormDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }
}
