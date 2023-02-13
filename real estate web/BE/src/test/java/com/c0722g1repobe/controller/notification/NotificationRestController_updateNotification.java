package com.c0722g1repobe.controller.notification;

import com.c0722g1repobe.dto.notification.NotificationDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class NotificationRestController_updateNotification {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Create by: AnhTDQ
     * Date created: 01/02/2023
     * Function: this function notification to test the validation of field title more specific is null
     */
    @Test
    public void updateNotification_title_19() throws Exception {
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setIdNotification(Long.valueOf(1));
        notificationDto.setTitle("");
        notificationDto.setPostingDate(LocalDate.parse("2023-02-01"));
        notificationDto.setContent("Trien khai cong viec thang 2 cho tung bo phan");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/notification/update")
                        .content(this.objectMapper.writeValueAsString(notificationDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * Create by: AnhTDQ
     * Date created: 01/02/2023
     * Function: this function notification to test the validation of field title more specific is empty
     */

    @Test
    public void updateNotification_title_20() throws Exception {
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setIdNotification(Long.valueOf(1));
        notificationDto.setTitle("  ");
        notificationDto.setPostingDate(LocalDate.parse("2023-02-01"));
        notificationDto.setContent("Trien khai cong viec thang 2 cho tung bo phan");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/notification/update")
                        .content(this.objectMapper.writeValueAsString(notificationDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * Create by: AnhTDQ
     * Date created: 01/02/2023
     * Function: this function notification to test the validation of field title more specific is containing special character
     */
    @Test
    public void updateNotification_title_21() throws Exception {
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setIdNotification(Long.valueOf(1));
        notificationDto.setTitle("Hop dau nam #$%^");
        notificationDto.setPostingDate(LocalDate.parse("2023-02-01"));
        notificationDto.setContent("Trien khai cong viec thang 2 cho tung bo phan");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/notification/update")
                        .content(this.objectMapper.writeValueAsString(notificationDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * Create by: AnhTDQ
     * Date created: 01/02/2023
     * Function: this function notification to test the validation of field title more specific is min length
     */
    @Test
    public void updateNotification_title_22() throws Exception {
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setIdNotification(Long.valueOf(1));
        notificationDto.setTitle("a");
        notificationDto.setPostingDate(LocalDate.parse("2023-02-01"));
        notificationDto.setContent("Trien khai cong viec thang 2 cho tung bo phan");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/notification/update")
                        .content(this.objectMapper.writeValueAsString(notificationDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * Create by: AnhTDQ
     * Date created: 01/02/2023
     * Function: this function notification to test the validation of field title more specific is max length
     */
    @Test
    public void updateNotification_title_23() throws Exception {
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setIdNotification(Long.valueOf(1));
        notificationDto.setTitle("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        notificationDto.setPostingDate(LocalDate.parse("2023-02-01"));
        notificationDto.setContent("Trien khai cong viec thang 2 cho tung bo phan");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/notification/update")
                        .content(this.objectMapper.writeValueAsString(notificationDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * Create by: AnhTDQ
     * Date created: 01/02/2023
     * Function: this function notification to test the validation of field postingDate more specific is null
     */
    @Test
    public void updateNotification_postingDate_19() throws Exception {
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setIdNotification(Long.valueOf(1));
        notificationDto.setTitle("Hop dau nam");
        notificationDto.setPostingDate(LocalDate.parse(""));
        notificationDto.setContent("Trien khai cong viec thang 2 cho tung bo phan");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/notification/update")
                        .content(this.objectMapper.writeValueAsString(notificationDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * Create by: AnhTDQ
     * Date created: 01/02/2023
     * Function: this function notification to test the validation of field postingDate more specific is empty
     */

    @Test
    public void updateNotification_postingDate_20() throws Exception {
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setIdNotification(Long.valueOf(1));
        notificationDto.setTitle("Hop dau nam");
        notificationDto.setPostingDate(LocalDate.parse("  "));
        notificationDto.setContent("Trien khai cong viec thang 2 cho tung bo phan");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/notification/update")
                        .content(this.objectMapper.writeValueAsString(notificationDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }


    /**
     * Create by: AnhTDQ
     * Date created: 01/02/2023
     * Function: this function notification to test the validation of field postingDate more specific is containing special character
     */
    @Test
    public void updateNotification_postingDate_21() throws Exception {
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setIdNotification(Long.valueOf(1));
        notificationDto.setTitle("Hop dau nam ");
        notificationDto.setPostingDate(LocalDate.parse("2023-02-01@#$$%"));
        notificationDto.setContent("Trien khai cong viec thang 2 cho tung bo phan");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/notification/update")
                        .content(this.objectMapper.writeValueAsString(notificationDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * Create by: AnhTDQ
     * Date created: 01/02/2023
     * Function: this function notification to test the validation of field postingDate more specific is min length
     */
    @Test
    public void updateNotification_postingDate_22() throws Exception {
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setIdNotification(Long.valueOf(1));
        notificationDto.setTitle("Hop dau nam");
        notificationDto.setPostingDate(LocalDate.parse("2"));
        notificationDto.setContent("Trien khai cong viec thang 2 cho tung bo phan");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/notification/update")
                        .content(this.objectMapper.writeValueAsString(notificationDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * Create by: AnhTDQ
     * Date created: 01/02/2023
     * Function: this function notification to test the validation of field postingDate more specific is max length
     */
    @Test
    public void updateNotification_postingDate_23() throws Exception {
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setIdNotification(Long.valueOf(1));
        notificationDto.setTitle("Hop dau nam");
        notificationDto.setPostingDate(LocalDate.parse("0202020202020202020020202020202020202"));
        notificationDto.setContent("Trien khai cong viec thang 2 cho tung bo phan");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/notification/update")
                        .content(this.objectMapper.writeValueAsString(notificationDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * Create by: AnhTDQ
     * Date created: 01/02/2023
     * Function: this function to check the validity of the posting date field More specifically, the format is not correct
     */
    @Test
    public void updateNotification_postingDate_99() throws Exception {
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setIdNotification(Long.valueOf(1));
        notificationDto.setTitle("Hop dau nam ");
        notificationDto.setPostingDate(LocalDate.parse("2023.02.01"));
        notificationDto.setContent("Trien khai cong viec thang 2 cho tung bo phan");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/notification/update")
                        .content(this.objectMapper.writeValueAsString(notificationDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * Create by: AnhTDQ
     * Date created: 01/02/2023
     * Function: this function notification to test the validation of field Content more specific is null
     */
    @Test
    public void updateNotification_content_19() throws Exception {
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setIdNotification(Long.valueOf(1));
        notificationDto.setTitle("Hop dau nam");
        notificationDto.setPostingDate(LocalDate.parse("2023-02-01"));
        notificationDto.setContent("");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/notification/update")
                        .content(this.objectMapper.writeValueAsString(notificationDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * Create by: AnhTDQ
     * Date created: 01/02/2023
     * Function: this function notification to test the validation of field content more specific is empty
     */

    @Test
    public void updateNotification_content_20() throws Exception {
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setIdNotification(Long.valueOf(1));
        notificationDto.setTitle("Hop dau nam");
        notificationDto.setPostingDate(LocalDate.parse("2023-02-01"));
        notificationDto.setContent("   ");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/notification/update")
                        .content(this.objectMapper.writeValueAsString(notificationDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * Create by: AnhTDQ
     * Date created: 01/02/2023
     * Function: this function notification to test the validation of field content  more specific is containing special character
     */
    @Test
    public void updateNotification_content_21() throws Exception {
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setIdNotification(Long.valueOf(1));
        notificationDto.setTitle("Hop dau nam");
        notificationDto.setPostingDate(LocalDate.parse("2023-02-01"));
        notificationDto.setContent("Trien khai cong viec thang 2 cho tung bo phan@#$%%$");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/notification/update")
                        .content(this.objectMapper.writeValueAsString(notificationDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * Create by: AnhTDQ
     * Date created: 01/02/2023
     * Function: this function notification to test the validation of field content  more specific is min length
     */
    @Test
    public void updateNotification_content_22() throws Exception {
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setIdNotification(Long.valueOf(1));
        notificationDto.setTitle("Hop dau nam");
        notificationDto.setPostingDate(LocalDate.parse("2023-02-01"));
        notificationDto.setContent("a");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/notification/update")
                        .content(this.objectMapper.writeValueAsString(notificationDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * Create by: AnhTDQ
     * Date created: 01/02/2023
     * Function: this function notification to test the validation of field content  more specific is max length
     */
    @Test
    public void updateNotification_content_23() throws Exception {
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setIdNotification(Long.valueOf(1));
        notificationDto.setTitle("Hop dau nam");
        notificationDto.setPostingDate(LocalDate.parse("2023-02-01"));
        notificationDto.setContent("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/notification/update")
                        .content(this.objectMapper.writeValueAsString(notificationDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * Create by: AnhTDQ
     * Date created: 01/02/2023
     * Function: this function notification to test the validation of field all right (is complete)
     */
    @Test
    public void createNotification_complete_24() throws Exception {
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setIdNotification(Long.valueOf(1));
        notificationDto.setTitle("Hop dau nam");
        notificationDto.setPostingDate(LocalDate.parse("2023-02-01"));
        notificationDto.setContent("Trien khai cong viec thang 2 cho tung bo phan");
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/notification/create")
                        .content(this.objectMapper.writeValueAsString(notificationDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

}
