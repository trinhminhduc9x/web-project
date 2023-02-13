package com.c0722g1repobe.controller;

import com.c0722g1repobe.dto.employee.EmployeeDto;
import com.c0722g1repobe.entity.account.Account;
import com.c0722g1repobe.entity.employee.Division;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeController_updateEmployee {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;
    @Test
    public void updateEmployee_item_19() throws Exception {
        EmployeeDto employeeDto = new EmployeeDto();
        this.mockMvc
                .perform(MockMvcRequestBuilders.patch("/api/employees/update/{id}", "null")
                        .content(this.objectMapper.writeValueAsString(employeeDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void updateEmployee_item_20() throws Exception {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setCodeEmployee("");
        employeeDto.setPhoneEmployee("");
        employeeDto.setNameEmployee("");
        employeeDto.setEmailEmployee("");
        employeeDto.setAddressEmployee("");
        employeeDto.setGenderEmployee(true);
        employeeDto.setDateOfBirth("");
        Division division = new Division();
        employeeDto.setDivision(division);
        Account account = new Account();
        employeeDto.setAccount(account);
        this.mockMvc
                .perform(MockMvcRequestBuilders.patch("/api/employees/update/{id}", "")
                        .content(this.objectMapper.writeValueAsString(employeeDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void updateEmployee_24() throws Exception {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setCodeEmployee("NV-2412");
        employeeDto.setPhoneEmployee("0384356242");
        employeeDto.setNameEmployee("Phan Thanh Long");
        employeeDto.setEmailEmployee("thanhhlongg2412@gmail.com");
        employeeDto.setAddressEmployee("Hải Lăng - Quảng Trị");
        employeeDto.setGenderEmployee(true);
        employeeDto.setDateOfBirth("2004-12-24");
        Division division = new Division();
        division.setIdDivision(1L);
        employeeDto.setDivision(division);
        Account account = new Account();
        account.setIdAccount(3L);
        employeeDto.setAccount(account);
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/api/employees/update/{id}", "1")
                        .content(this.objectMapper.writeValueAsString(employeeDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }
}
