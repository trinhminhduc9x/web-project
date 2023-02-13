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
public class DataFormRestController_deleteByIdDataForm {

    @Autowired
    private MockMvc mockMvc ;

    /**
     * Create by: DungND,
     * Date created: 01/02/2023
     * Function: delete dataForm item id == null
     *
     * @return error status code NOT FOUND
     */
    @Test
    public void deleteByIdDataForm_id_25() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.delete("/api/form/delete/{id}","null"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * Create by: DungND,
     * Date created: 01/02/2023
     * Function: delete dataForm  with item id empty
     *
     * @return error status code
     */
    @Test
    public void deleteByIdDataForm_id_26() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.delete("/api/form/delete/{id}",""))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * Create by: DungND,
     * Date created: 01/02/2023
     * Function: delete dataForm  with item id not have in database
     *
     * @return error status code
     */
    @Test
    public void deleteByIdDataForm_id_27() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.delete("/api/form/delete/{id}",20))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
    /**
     * Create by: DungND,
     * Date created: 01/02/2023
     * Function: delete dataForm with item id valid (exists in dabatabase)
     *
     * @return a dataForm with valid infor
     */
    @Test
    public void deleteByIdDataForm_id_28() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.delete("/api/form/delete/{id}",3))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

}
