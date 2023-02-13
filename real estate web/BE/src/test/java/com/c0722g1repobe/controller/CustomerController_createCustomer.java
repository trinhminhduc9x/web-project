package com.c0722g1repobe.controller;

import com.c0722g1repobe.dto.customer.CustomerDto;
import com.c0722g1repobe.entity.account.Account;
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
public class CustomerController_createCustomer {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createCustomer_name_14() throws Exception {

        CustomerDto customerDto = new CustomerDto();
        customerDto.setNameCustomer("");
        customerDto.setDateOfBirthCustomer("11/03/2001");
        customerDto.setGenderCustomer(0);
        customerDto.setAddressCustomer("Khu phố 2");
        customerDto.setPhoneCustomer1("098762123");
        customerDto.setEmailCustomer("Kakao@gmail.com");
        customerDto.setEncryptPassword("123451");
        customerDto.setIdCardCustomer("9876910823");

        Account account = new Account();
        account.setIdAccount(3L);
        account.setEncryptPassword(account.getEncryptPassword());

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/v1/customer/create")
                        .content(this.objectMapper.writeValueAsString(customerDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void createCustomer_dateOfBirth_14() throws Exception {

        CustomerDto customerDto = new CustomerDto();
        customerDto.setNameCustomer("Nguyễn Văn Huy");
        customerDto.setDateOfBirthCustomer("");
        customerDto.setGenderCustomer(0);
        customerDto.setAddressCustomer("Khu phố 2");
        customerDto.setPhoneCustomer1("098762123");
        customerDto.setEmailCustomer("Kakao@gmail.com");
        customerDto.setEncryptPassword("123451");
        customerDto.setIdCardCustomer("9876910823");

        Account account = new Account();
        account.setIdAccount(3L);
        account.setEncryptPassword(account.getEncryptPassword());

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/v1/customer/create")
                        .content(this.objectMapper.writeValueAsString(customerDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void createCustomer_gender_14() throws Exception {

        CustomerDto customerDto = new CustomerDto();
        customerDto.setNameCustomer("Nguyễn Văn Huy");
        customerDto.setDateOfBirthCustomer("11/02/2000");
        customerDto.setGenderCustomer(null);
        customerDto.setAddressCustomer("Khu phố 2");
        customerDto.setPhoneCustomer1("098762123");
        customerDto.setEmailCustomer("Kakao@gmail.com");
        customerDto.setEncryptPassword("123451");
        customerDto.setIdCardCustomer("9876910823");

        Account account = new Account();
        account.setIdAccount(3L);
        account.setEncryptPassword(account.getEncryptPassword());

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/v1/customer/create")
                        .content(this.objectMapper.writeValueAsString(customerDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void createCustomer_address_14() throws Exception {

        CustomerDto customerDto = new CustomerDto();
        customerDto.setNameCustomer("Nguyễn Văn Huy");
        customerDto.setDateOfBirthCustomer("11/02/2000");
        customerDto.setGenderCustomer(0);
        customerDto.setAddressCustomer("");
        customerDto.setPhoneCustomer1("098762123");
        customerDto.setEmailCustomer("Kakao@gmail.com");
        customerDto.setEncryptPassword("123451");
        customerDto.setIdCardCustomer("9876910823");

        Account account = new Account();
        account.setIdAccount(3L);
        account.setEncryptPassword(account.getEncryptPassword());

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/v1/customer/create")
                        .content(this.objectMapper.writeValueAsString(customerDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void createCustomer_phone_14() throws Exception {

        CustomerDto customerDto = new CustomerDto();
        customerDto.setNameCustomer("Nguyễn Văn Huy");
        customerDto.setDateOfBirthCustomer("11/02/2000");
        customerDto.setGenderCustomer(0);
        customerDto.setAddressCustomer("Khu phố 2");
        customerDto.setPhoneCustomer1("");
        customerDto.setEmailCustomer("Kakao@gmail.com");
        customerDto.setEncryptPassword("123451");
        customerDto.setIdCardCustomer("9876910823");

        Account account = new Account();
        account.setIdAccount(3L);
        account.setEncryptPassword(account.getEncryptPassword());

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/v1/customer/create")
                        .content(this.objectMapper.writeValueAsString(customerDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void createCustomer_email_14() throws Exception {

        CustomerDto customerDto = new CustomerDto();
        customerDto.setNameCustomer("Nguyễn Văn Huy");
        customerDto.setDateOfBirthCustomer("11/02/2000");
        customerDto.setGenderCustomer(0);
        customerDto.setAddressCustomer("Khu phố 2");
        customerDto.setPhoneCustomer1("0987654312");
        customerDto.setEmailCustomer("");
        customerDto.setEncryptPassword("123451");
        customerDto.setIdCardCustomer("9876910823");

        Account account = new Account();
        account.setIdAccount(3L);
        account.setEncryptPassword(account.getEncryptPassword());

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/v1/customer/create")
                        .content(this.objectMapper.writeValueAsString(customerDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void createCustomer_password_14() throws Exception {

        CustomerDto customerDto = new CustomerDto();
        customerDto.setNameCustomer("Nguyễn Văn Huy");
        customerDto.setDateOfBirthCustomer("11/02/2000");
        customerDto.setGenderCustomer(0);
        customerDto.setAddressCustomer("Khu phố 2");
        customerDto.setPhoneCustomer1("0987654312");
        customerDto.setEmailCustomer("mao@gmail.com");
        customerDto.setEncryptPassword("");
        customerDto.setIdCardCustomer("9876910823");

        Account account = new Account();
        account.setIdAccount(3L);
        account.setEncryptPassword(account.getEncryptPassword());

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/v1/customer/create")
                        .content(this.objectMapper.writeValueAsString(customerDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void createCustomer_idCard_14() throws Exception {

        CustomerDto customerDto = new CustomerDto();
        customerDto.setNameCustomer("Nguyễn Văn Huy");
        customerDto.setDateOfBirthCustomer("11/02/2000");
        customerDto.setGenderCustomer(0);
        customerDto.setAddressCustomer("Khu phố 2");
        customerDto.setPhoneCustomer1("0987654312");
        customerDto.setEmailCustomer("mao@gmail.com");
        customerDto.setEncryptPassword("123281");
        customerDto.setIdCardCustomer("");

        Account account = new Account();
        account.setIdAccount(3L);
        account.setEncryptPassword(account.getEncryptPassword());

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/v1/customer/create")
                        .content(this.objectMapper.writeValueAsString(customerDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * Create by: HuyNV
     * Date created : 01/02/2023
     * Function : This function is used to check the authenticated school names
     *
     * @throws Exception
     */
    @Test
    public void createCustomer_30() throws Exception {

        CustomerDto customerDto = new CustomerDto();
        customerDto.setNameCustomer("");
        customerDto.setDateOfBirthCustomer("");
        customerDto.setGenderCustomer(null);
        customerDto.setAddressCustomer("");
        customerDto.setPhoneCustomer1("");
        customerDto.setEmailCustomer("m");
        customerDto.setEncryptPassword("");
        customerDto.setIdCardCustomer("");

        Account account = new Account();
        account.setIdAccount(3L);
        account.setEncryptPassword(account.getEncryptPassword());

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/v1/customer/create")
                        .content(this.objectMapper.writeValueAsString(customerDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void createCustomer_name_15() throws Exception {

        CustomerDto customerDto = new CustomerDto();
        customerDto.setNameCustomer("@#$%");
        customerDto.setDateOfBirthCustomer("11/03/2001");
        customerDto.setGenderCustomer(0);
        customerDto.setAddressCustomer("Khu phố 2");
        customerDto.setPhoneCustomer1("098762123");
        customerDto.setEmailCustomer("Kakao@gmail.com");
        customerDto.setEncryptPassword("123451");
        customerDto.setIdCardCustomer("9876910823");

        Account account = new Account();
        account.setIdAccount(3L);
        account.setEncryptPassword(account.getEncryptPassword());

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/v1/customer/create")
                        .content(this.objectMapper.writeValueAsString(customerDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void createCustomer_phone_15() throws Exception {

        CustomerDto customerDto = new CustomerDto();
        customerDto.setNameCustomer("Nguyễn Văn Huy");
        customerDto.setDateOfBirthCustomer("11/03/2001");
        customerDto.setGenderCustomer(0);
        customerDto.setAddressCustomer("Khu phố 2");
        customerDto.setPhoneCustomer1("zxcxzasdas");
        customerDto.setEmailCustomer("Kakao@gmail.com");
        customerDto.setEncryptPassword("123451");
        customerDto.setIdCardCustomer("9876910823");

        Account account = new Account();
        account.setIdAccount(3L);
        account.setEncryptPassword(account.getEncryptPassword());

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/v1/customer/create")
                        .content(this.objectMapper.writeValueAsString(customerDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void createCustomer_email_15() throws Exception {

        CustomerDto customerDto = new CustomerDto();
        customerDto.setNameCustomer("@#$%");
        customerDto.setDateOfBirthCustomer("11/03/2001");
        customerDto.setGenderCustomer(0);
        customerDto.setAddressCustomer("Khu phố 2");
        customerDto.setPhoneCustomer1("098762123");
        customerDto.setEmailCustomer("mkzasi291");
        customerDto.setEncryptPassword("123451");
        customerDto.setIdCardCustomer("9876910823");

        Account account = new Account();
        account.setIdAccount(3L);
        account.setEncryptPassword(account.getEncryptPassword());

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/v1/customer/create")
                        .content(this.objectMapper.writeValueAsString(customerDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void createCustomer_idCard_15() throws Exception {

        CustomerDto customerDto = new CustomerDto();
        customerDto.setNameCustomer("@#$%");
        customerDto.setDateOfBirthCustomer("11/03/2001");
        customerDto.setGenderCustomer(0);
        customerDto.setAddressCustomer("Khu phố 2");
        customerDto.setPhoneCustomer1("098762123");
        customerDto.setEmailCustomer("Kakao@gmail.com");
        customerDto.setEncryptPassword("123451");
        customerDto.setIdCardCustomer("mkasjdiasj");

        Account account = new Account();
        account.setIdAccount(3L);
        account.setEncryptPassword(account.getEncryptPassword());

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/v1/customer/create")
                        .content(this.objectMapper.writeValueAsString(customerDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void createCustomer_name_16() throws Exception {

        CustomerDto customerDto = new CustomerDto();
        customerDto.setNameCustomer("m");
        customerDto.setDateOfBirthCustomer("11/03/2001");
        customerDto.setGenderCustomer(0);
        customerDto.setAddressCustomer("Khu phố 2");
        customerDto.setPhoneCustomer1("098762123");
        customerDto.setEmailCustomer("Kakao@gmail.com");
        customerDto.setEncryptPassword("123451");
        customerDto.setIdCardCustomer("9876910823");

        Account account = new Account();
        account.setIdAccount(3L);
        account.setEncryptPassword(account.getEncryptPassword());

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/v1/customer/create")
                        .content(this.objectMapper.writeValueAsString(customerDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void createCustomer_address_16() throws Exception {

        CustomerDto customerDto = new CustomerDto();
        customerDto.setNameCustomer("Nguyễn Văn Huy");
        customerDto.setDateOfBirthCustomer("11/03/2001");
        customerDto.setGenderCustomer(0);
        customerDto.setAddressCustomer("b1");
        customerDto.setPhoneCustomer1("098762123");
        customerDto.setEmailCustomer("Kakao@gmail.com");
        customerDto.setEncryptPassword("123451");
        customerDto.setIdCardCustomer("9876910823");

        Account account = new Account();
        account.setIdAccount(3L);
        account.setEncryptPassword(account.getEncryptPassword());

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/v1/customer/create")
                        .content(this.objectMapper.writeValueAsString(customerDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void createCustomer_phone_16() throws Exception {

        CustomerDto customerDto = new CustomerDto();
        customerDto.setNameCustomer("Nguyễn Văn Huy");
        customerDto.setDateOfBirthCustomer("11/03/2001");
        customerDto.setGenderCustomer(0);
        customerDto.setAddressCustomer("Khu phố 2 ");
        customerDto.setPhoneCustomer1("098");
        customerDto.setEmailCustomer("Kakao@gmail.com");
        customerDto.setEncryptPassword("123451");
        customerDto.setIdCardCustomer("9876910823");

        Account account = new Account();
        account.setIdAccount(3L);
        account.setEncryptPassword(account.getEncryptPassword());

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/v1/customer/create")
                        .content(this.objectMapper.writeValueAsString(customerDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void createCustomer_password_16() throws Exception {

        CustomerDto customerDto = new CustomerDto();
        customerDto.setNameCustomer("Nguyễn Văn Huy");
        customerDto.setDateOfBirthCustomer("11/03/2001");
        customerDto.setGenderCustomer(0);
        customerDto.setAddressCustomer("Khu phố 2 ");
        customerDto.setPhoneCustomer1("0987654321");
        customerDto.setEmailCustomer("Kakao@gmail.com");
        customerDto.setEncryptPassword("1");
        customerDto.setIdCardCustomer("9876910823");

        Account account = new Account();
        account.setIdAccount(3L);
        account.setEncryptPassword(account.getEncryptPassword());

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/v1/customer/create")
                        .content(this.objectMapper.writeValueAsString(customerDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void createCustomer_idCard_16() throws Exception {

        CustomerDto customerDto = new CustomerDto();
        customerDto.setNameCustomer("Nguyễn Văn Huy");
        customerDto.setDateOfBirthCustomer("11/03/2001");
        customerDto.setGenderCustomer(0);
        customerDto.setAddressCustomer("Khu phố 2 ");
        customerDto.setPhoneCustomer1("0987654321");
        customerDto.setEmailCustomer("Kakao@gmail.com");
        customerDto.setEncryptPassword("123456");
        customerDto.setIdCardCustomer("98");

        Account account = new Account();
        account.setIdAccount(3L);
        account.setEncryptPassword(account.getEncryptPassword());

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/v1/customer/create")
                        .content(this.objectMapper.writeValueAsString(customerDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void createCustomer_name_17() throws Exception {

        CustomerDto customerDto = new CustomerDto();
        customerDto.setNameCustomer("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        customerDto.setDateOfBirthCustomer("11/03/2001");
        customerDto.setGenderCustomer(0);
        customerDto.setAddressCustomer("Khu phố 2");
        customerDto.setPhoneCustomer1("098762123");
        customerDto.setEmailCustomer("Kakao@gmail.com");
        customerDto.setEncryptPassword("123451");
        customerDto.setIdCardCustomer("9876910823");

        Account account = new Account();
        account.setIdAccount(3L);
        account.setEncryptPassword(account.getEncryptPassword());

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/v1/customer/create")
                        .content(this.objectMapper.writeValueAsString(customerDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void createCustomer_address_17() throws Exception {

        CustomerDto customerDto = new CustomerDto();
        customerDto.setNameCustomer("Nguyễn Văn Huy");
        customerDto.setDateOfBirthCustomer("11/03/2001");
        customerDto.setGenderCustomer(0);
        customerDto.setAddressCustomer("Khu phố 22222222222222222222222222222zzzzzzzzzzzzzaaaaa");
        customerDto.setPhoneCustomer1("098762123");
        customerDto.setEmailCustomer("Kakao@gmail.com");
        customerDto.setEncryptPassword("123451");
        customerDto.setIdCardCustomer("9876910823");

        Account account = new Account();
        account.setIdAccount(3L);
        account.setEncryptPassword(account.getEncryptPassword());

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/v1/customer/create")
                        .content(this.objectMapper.writeValueAsString(customerDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void createCustomer_phone_17() throws Exception {

        CustomerDto customerDto = new CustomerDto();
        customerDto.setNameCustomer("Nguyễn Văn Huy");
        customerDto.setDateOfBirthCustomer("11/03/2001");
        customerDto.setGenderCustomer(0);
        customerDto.setAddressCustomer("Khu phố 2");
        customerDto.setPhoneCustomer1("09876212333333333331222222222212");
        customerDto.setEmailCustomer("Kakao@gmail.com");
        customerDto.setEncryptPassword("123451");
        customerDto.setIdCardCustomer("9876910823");

        Account account = new Account();
        account.setIdAccount(3L);
        account.setEncryptPassword(account.getEncryptPassword());

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/v1/customer/create")
                        .content(this.objectMapper.writeValueAsString(customerDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void createCustomer_password_17() throws Exception {

        CustomerDto customerDto = new CustomerDto();
        customerDto.setNameCustomer("Nguyễn Văn Huy");
        customerDto.setDateOfBirthCustomer("11/03/2001");
        customerDto.setGenderCustomer(0);
        customerDto.setAddressCustomer("Khu phố 2");
        customerDto.setPhoneCustomer1("098762123");
        customerDto.setEmailCustomer("Kakao@gmail.com");
        customerDto.setEncryptPassword("123451222222222222222222222221111111");
        customerDto.setIdCardCustomer("9876910823");

        Account account = new Account();
        account.setIdAccount(3L);
        account.setEncryptPassword(account.getEncryptPassword());

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/v1/customer/create")
                        .content(this.objectMapper.writeValueAsString(customerDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void createCustomer_idCard_17() throws Exception {

        CustomerDto customerDto = new CustomerDto();
        customerDto.setNameCustomer("Nguyễn Văn Huy");
        customerDto.setDateOfBirthCustomer("11/03/2001");
        customerDto.setGenderCustomer(0);
        customerDto.setAddressCustomer("Khu phố 2");
        customerDto.setPhoneCustomer1("098762123");
        customerDto.setEmailCustomer("Kakao@gmail.com");
        customerDto.setEncryptPassword("123456");
        customerDto.setIdCardCustomer("9876910823333333333333333333333333333");

        Account account = new Account();
        account.setIdAccount(3L);
        account.setEncryptPassword(account.getEncryptPassword());

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/v1/customer/create")
                        .content(this.objectMapper.writeValueAsString(customerDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * Create by: HuyNV
     * Date created : 01/02/2023
     * Function : This function is used to check the authentic name of the school date not under 18 years old
     *
     * @throws Exception
     */
    @Test
    public void createCustomer_dateBirth_31() throws Exception {

        CustomerDto customerDto = new CustomerDto();
        customerDto.setNameCustomer("Nguyễn Văn Huy");
        customerDto.setDateOfBirthCustomer("11/03/2023");
        customerDto.setGenderCustomer(0);
        customerDto.setAddressCustomer("Khu phố 2");
        customerDto.setPhoneCustomer1("098762123");
        customerDto.setEmailCustomer("Kakao@gmail.com");
        customerDto.setEncryptPassword("123456");
        customerDto.setIdCardCustomer("9876910823333333333333333333333333333");

        Account account = new Account();
        account.setIdAccount(3L);
        account.setEncryptPassword(account.getEncryptPassword());

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/v1/customer/create")
                        .content(this.objectMapper.writeValueAsString(customerDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * Create by: HuyNV
     * Date created : 01/02/2023
     * Function : This function is used to check the existing email school name
     *
     * @throws Exception
     */
    @Test
    public void createCustomer_email_31() throws Exception {

        CustomerDto customerDto = new CustomerDto();
        customerDto.setNameCustomer("Nguyễn Văn Huy");
        customerDto.setDateOfBirthCustomer("11/03/2000");
        customerDto.setGenderCustomer(0);
        customerDto.setAddressCustomer("Khu phố 2");
        customerDto.setPhoneCustomer1("098762123");
        customerDto.setEmailCustomer("Kakao@gmail.com");
        customerDto.setEncryptPassword("123456");
        customerDto.setIdCardCustomer("9876910823333333333333333333333333333");

        Account account = new Account();
        account.setIdAccount(3L);
        account.setEncryptPassword(account.getEncryptPassword());

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/v1/customer/create")
                        .content(this.objectMapper.writeValueAsString(customerDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}
