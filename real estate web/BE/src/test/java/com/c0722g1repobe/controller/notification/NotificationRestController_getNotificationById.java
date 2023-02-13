package com.c0722g1repobe.controller.notification;

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
public class NotificationRestController_getNotificationById {
    @Autowired
    private MockMvc mockMvc;

    /**
     * Create by: AnhTDQ,
     * Date created: 01/02/2023
     * Function: get a notification with item id == null
     *
     * @return error status code
     */
    @Test
    public void getNotificationById_id_1() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/notification/find/{id}",null))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * Create by: AnhTDQ,
     * Date created: 01/02/2023
     * Function: get a notification with item id empty
     *
     * @return error status code
     */
    @Test
    public void getNotificationById_id_2() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/notification/find/{id}","  "))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * Create by: AnhTDQ,
     * Date created: 01/02/2023
     * Function: get a notification with item id not have in database
     *
     * @return error status code
     */
    @Test
    public void getNotificationById_id_3() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/notification/find/{id}",100))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * Create by: AnhTDQ,
     * Date created: 01/02/2023
     * Function: get a notification with item id valid (exists in dabatabase)
     *
     * @return a notification with valid infor
     */
    @Test
    public void getNotificationById_4() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/notification/find/{id}",2))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("id").value(2))
                .andExpect(jsonPath("title").value("Họp triển khai công việc đầu năm"))
                .andExpect(jsonPath("posting_date").value("2023-02-01"))
                .andExpect(jsonPath("content").value("Triển khai công việc tháng 2 cho từng bộ phận"));
    }

}
