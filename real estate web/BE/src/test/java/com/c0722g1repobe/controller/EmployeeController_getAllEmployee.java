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
public class EmployeeController_getAllEmployee {

    @Autowired
    private MockMvc mockMvc;

    /**
     * This function use to test list employee of all field search is null
     *
     * @author NhanUQ
     * @Date 02/01/2023
     */
    @Test
    public void getAllEmployee_7() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/employees/employee-list?codeSearch=null&nameSearch=null&emailSearch=null&nameDivisionSearch=null&page=0"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * This function use to test list employee of all field search is "", page = 0
     *
     * @author NhanUQ
     * @Date 02/01/2023
     */
    @Test
    public void getAllEmployee_8() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/employees/employee-list?codeSearch=&nameSearch=&emailSearch=&nameDivisionSearch=&page=0"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("totalPages").value(2))
                .andExpect(jsonPath("totalElements").value(10))
                .andExpect(jsonPath("content[0].idEmployee").value(3))
                .andExpect(jsonPath("content[0].codeEmployee").value("NV-003"))
                .andExpect(jsonPath("content[0].nameEmployee").value("Nguy???n B???nh Ph??t"))
                .andExpect(jsonPath("content[0].dateOfBirth").value("1997-12-09"))
                .andExpect(jsonPath("content[0].phoneEmployee").value("0902341231"))
                .andExpect(jsonPath("content[0].genderEmployee").value(true))
                .andExpect(jsonPath("content[0].emailEmployee").value("phatphat@gmail.com"))
                .andExpect(jsonPath("content[0].nameDivision").value("Qu???n l??"))
                .andExpect(jsonPath("content[4].idEmployee").value(9))
                .andExpect(jsonPath("content[4].codeEmployee").value("NV-009"))
                .andExpect(jsonPath("content[4].nameEmployee").value("Nguy???n Th??? Thu"))
                .andExpect(jsonPath("content[4].dateOfBirth").value("1995-09-02"))
                .andExpect(jsonPath("content[4].phoneEmployee").value("0908565656"))
                .andExpect(jsonPath("content[4].genderEmployee").value(false))
                .andExpect(jsonPath("content[4].emailEmployee").value("thunguyen@gmail.com"))
                .andExpect(jsonPath("content[4].nameDivision").value("K??? to??n"));
    }

    /**
     * This function use to test list employee of code search is NV-111
     *
     * @author NhanUQ
     * @Date 02/01/2023
     */
    @Test
    public void getAllEmployee_codeSearch_9() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/employees/employee-list?codeSearch=NV-111&nameSearch=&emailSearch=&nameDivisionSearch=&page=0"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * This function use to test list employee of name search is Nhan
     *
     * @author NhanUQ
     * @Date 02/01/2023
     */
    @Test
    public void getAllEmployee_nameSearch_9() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/employees/employee-list?codeSearch=&nameSearch=Nhan&emailSearch=&nameDivisionSearch=&page=0"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * This function use to test list employee of email search is nhanuq@gmail.com
     *
     * @author NhanUQ
     * @Date 02/01/2023
     */
    @Test
    public void getAllEmployee_emailSearch_9() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/employees/employee-list?codeSearch=&nameSearch=&emailSearch=nhanuq@gmail.com&nameDivisionSearch=&page=0"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * This function use to test list employee of name division search is Giam Doc
     *
     * @author NhanUQ
     * @Date 02/01/2023
     */
    @Test
    public void getAllEmployee_nameDivisionSearch_9() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/employees/employee-list?codeSearch=&nameSearch=&emailSearch=&nameDivisionSearch=Giam Doc&page=0"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * This function use to test list employee of all field search is character special
     *
     * @author NhanUQ
     * @Date 02/01/2023
     */
    @Test
    public void getAllEmployee_search_9() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/employees/employee-list?codeSearch=@&nameSearch=*&emailSearch=$&nameDivisionSearch=.&page=0"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * This function use to test list employee of name search is Tr???n Th??? H??o but flag_deleted = true
     *
     * @author NhanUQ
     * @Date 02/01/2023
     */
    @Test
    public void getAllEmployee_nameEmployee_10() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/employees/employee-list?codeSearch=&nameSearch=Tr???n Th??? H??o&emailSearch=&nameDivisionSearch=&page=0"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    /**
     * This function use to test list employee of code search is NV-003, page = 0
     *
     * @author NhanUQ
     * @Date 02/01/2023
     */
    @Test
    public void getAllEmployee_codeEmployee_11() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/employees/employee-list?codeSearch=NV-003&nameSearch=&emailSearch=&nameDivisionSearch=&page=0"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("totalPages").value(1))
                .andExpect(jsonPath("totalElements").value(1))
                .andExpect(jsonPath("content[0].idEmployee").value(3))
                .andExpect(jsonPath("content[0].codeEmployee").value("NV-003"))
                .andExpect(jsonPath("content[0].nameEmployee").value("Nguy???n B???nh Ph??t"))
                .andExpect(jsonPath("content[0].dateOfBirth").value("1997-12-09"))
                .andExpect(jsonPath("content[0].phoneEmployee").value("0902341231"))
                .andExpect(jsonPath("content[0].genderEmployee").value(true))
                .andExpect(jsonPath("content[0].emailEmployee").value("phatphat@gmail.com"))
                .andExpect(jsonPath("content[0].nameDivision").value("Qu???n l??"));
    }

    /**
     * This function use to test list employee of name search is T, page = 0
     *
     * @author NhanUQ
     * @Date 02/01/2023
     */
    @Test
    public void getAllEmployee_nameEmployee_11() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/employees/employee-list?codeSearch=&nameSearch=T&emailSearch=&nameDivisionSearch=&page=0"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("totalPages").value(1))
                .andExpect(jsonPath("totalElements").value(5))
                .andExpect(jsonPath("content[0].idEmployee").value(3))
                .andExpect(jsonPath("content[0].codeEmployee").value("NV-003"))
                .andExpect(jsonPath("content[0].nameEmployee").value("Nguy???n B???nh Ph??t"))
                .andExpect(jsonPath("content[0].dateOfBirth").value("1997-12-09"))
                .andExpect(jsonPath("content[0].phoneEmployee").value("0902341231"))
                .andExpect(jsonPath("content[0].genderEmployee").value(true))
                .andExpect(jsonPath("content[0].emailEmployee").value("phatphat@gmail.com"))
                .andExpect(jsonPath("content[0].nameDivision").value("Qu???n l??"))
                .andExpect(jsonPath("content[4].idEmployee").value(7))
                .andExpect(jsonPath("content[4].codeEmployee").value("NV-007"))
                .andExpect(jsonPath("content[4].nameEmployee").value("Phan Th??? Oanh"))
                .andExpect(jsonPath("content[4].dateOfBirth").value("1998-04-30"))
                .andExpect(jsonPath("content[4].phoneEmployee").value("0908343434"))
                .andExpect(jsonPath("content[4].genderEmployee").value(false))
                .andExpect(jsonPath("content[4].emailEmployee").value("oanhphan@gmail.com"))
                .andExpect(jsonPath("content[4].nameDivision").value("Nh??n vi??n"));
    }

    /**
     * This function use to test list employee of email search is oanhphan@gmail.com, page = 0
     *
     * @author NhanUQ
     * @Date 02/01/2023
     */
    @Test
    public void getAllEmployee_emailEmployee_11() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/employees/employee-list?codeSearch=&nameSearch=&emailSearch=oanhphan@gmail.com&nameDivisionSearch=&page=0"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("totalPages").value(1))
                .andExpect(jsonPath("totalElements").value(1))
                .andExpect(jsonPath("content[0].idEmployee").value(7))
                .andExpect(jsonPath("content[0].codeEmployee").value("NV-007"))
                .andExpect(jsonPath("content[0].nameEmployee").value("Phan Th??? Oanh"))
                .andExpect(jsonPath("content[0].dateOfBirth").value("1998-04-30"))
                .andExpect(jsonPath("content[0].phoneEmployee").value("0908343434"))
                .andExpect(jsonPath("content[0].genderEmployee").value(false))
                .andExpect(jsonPath("content[0].emailEmployee").value("oanhphan@gmail.com"))
                .andExpect(jsonPath("content[0].nameDivision").value("Nh??n vi??n"));
    }

    /**
     * This function use to test list employee of name division search is Tr?????ng ph??ng, page = 0
     *
     * @author NhanUQ
     * @Date 02/01/2023
     */
    @Test
    public void getAllEmployee_nameDivisionSearch_11() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/employees/employee-list?codeSearch=&nameSearch=&emailSearch=&nameDivisionSearch=Tr?????ng ph??ng&page=0"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("totalPages").value(1))
                .andExpect(jsonPath("totalElements").value(2))
                .andExpect(jsonPath("content[0].idEmployee").value(2))
                .andExpect(jsonPath("content[0].codeEmployee").value("NV-002"))
                .andExpect(jsonPath("content[0].nameEmployee").value("L?? V??n B??nh"))
                .andExpect(jsonPath("content[0].dateOfBirth").value("1980-04-09"))
                .andExpect(jsonPath("content[0].phoneEmployee").value("0934212314"))
                .andExpect(jsonPath("content[0].genderEmployee").value(true))
                .andExpect(jsonPath("content[0].emailEmployee").value("binhlv@gmail.com"))
                .andExpect(jsonPath("content[0].nameDivision").value("Tr?????ng ph??ng"))
                .andExpect(jsonPath("content[1].idEmployee").value(10))
                .andExpect(jsonPath("content[1].codeEmployee").value("NV-010"))
                .andExpect(jsonPath("content[1].nameEmployee").value("Phan Anh Tu???n"))
                .andExpect(jsonPath("content[1].dateOfBirth").value("1994-12-24"))
                .andExpect(jsonPath("content[1].phoneEmployee").value("0908676767"))
                .andExpect(jsonPath("content[1].genderEmployee").value(true))
                .andExpect(jsonPath("content[1].emailEmployee").value("tuananh@gmail.com"))
                .andExpect(jsonPath("content[1].nameDivision").value("Tr?????ng ph??ng"));

    }

    /**
     * This function use to test list employee of all field search is codeSearch=10, nameSearch=Tu???n, emailSearch=tuananh, nameDivisionSearch=Tr?????ng ph??ng, page = 0
     *
     * @author NhanUQ
     * @Date 02/01/2023
     */
    @Test
    public void getAllEmployee_allSearch_11() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/employees/employee-list?codeSearch=10&nameSearch=Tu???n&emailSearch=tuananh&nameDivisionSearch=Tr?????ng ph??ng&page=0"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("totalPages").value(1))
                .andExpect(jsonPath("totalElements").value(1))
                .andExpect(jsonPath("content[0].idEmployee").value(10))
                .andExpect(jsonPath("content[0].codeEmployee").value("NV-010"))
                .andExpect(jsonPath("content[0].nameEmployee").value("Phan Anh Tu???n"))
                .andExpect(jsonPath("content[0].dateOfBirth").value("1994-12-24"))
                .andExpect(jsonPath("content[0].phoneEmployee").value("0908676767"))
                .andExpect(jsonPath("content[0].genderEmployee").value(true))
                .andExpect(jsonPath("content[0].emailEmployee").value("tuananh@gmail.com"))
                .andExpect(jsonPath("content[0].nameDivision").value("Tr?????ng ph??ng"));

    }
}
