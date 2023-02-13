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
public class DataFormRestController_findByIdDataForm {
    @Autowired
    private MockMvc mockMvc ;


    /**
     * Create by: DungND,
     * Date created: 01/02/2023
     * Function: get a dataForm with item id == null
     *
     * @return error status code
     */
    @Test
    public void findByIdDataForm_id_1() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/form/find/{id}","null"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * Create by: DungND,
     * Date created: 01/02/2023
     * Function: get a dataForm with item id empty
     *
     * @return error status code
     */
    @Test
    public void findByIdDataForm_id_2() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/form/find/{id}",""))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * Create by: DungND,
     * Date created: 01/02/2023
     * Function: get a dataForm with item id not have in database
     *
     * @return error status code
     */
    @Test
    public void findByIdDataForm_id_3() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/form/find/{id}",20))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    /**
     * Create by: DungND,
     * Date created: 01/02/2023
     * Function: get a dataForm with item id valid (exists in dabatabase)
     *
     * @return a guide with valid infor
     */
    @Test
    public void findByIdDataForm_id_4() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/form/find/{id}", 2))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("idDataForm").value(2))
                .andExpect(jsonPath("codeDataForm").value("HS-113"))
                .andExpect(jsonPath("contentDataForm").value("Hợp Đồng Thẩm Định Gía Bất Động Sản"))
                .andExpect(jsonPath("detailForm.urlDetailForm").value("Link2"));

    }

}
