package com.c0722g1repobe.dto.employee;

import com.c0722g1repobe.entity.account.Account;
import com.c0722g1repobe.entity.employee.Division;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.*;

public class EmployeeDto implements Validator {
    private Long idEmployee;
    @NotBlank(message = "Vui lòng nhập mã!")
    @Pattern(regexp = "^[N][V][-][0-9]{4}$", message = "Mã nhân viên chưa đúng định dạng")
    private String codeEmployee;
    @NotBlank(message = "Vui lòng nhập tên!")
    @Size(max = 50)
    private String nameEmployee;
    @NotNull(message = "Vui lòng thêm ngày sinh")
    private String dateOfBirth;
    @NotNull(message = "Vui lòng thêm giới tính")
    private boolean genderEmployee;
    @NotNull
    @Size(max = 11)
    @NotBlank(message = "Số điện thoại không được để trống.")
    private String phoneEmployee;
    @NotBlank(message = "Email không được để trống.")
    @Email(message = "Địa chỉ email phải đúng định dạng.")
    private String emailEmployee;
    @NotBlank(message = "Địa chỉ không được để trống.")
    private String addressEmployee;
    private boolean flagDeleted = false;
    @ManyToOne
    private Division division;
    @OneToOne
    private Account account;

    public EmployeeDto() {
    }

    public EmployeeDto(Long idEmployee, String codeEmployee, String nameEmployee, String dateOfBirth, boolean genderEmployee, String phoneEmployee, String emailEmployee, String addressEmployee, boolean flagDeleted, Division division, Account account) {
        this.idEmployee = idEmployee;
        this.codeEmployee = codeEmployee;
        this.nameEmployee = nameEmployee;
        this.dateOfBirth = dateOfBirth;
        this.genderEmployee = genderEmployee;
        this.phoneEmployee = phoneEmployee;
        this.emailEmployee = emailEmployee;
        this.addressEmployee = addressEmployee;
        this.flagDeleted = flagDeleted;
        this.division = division;
        this.account = account;
    }

    public Long getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Long idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getCodeEmployee() {
        return codeEmployee;
    }

    public void setCodeEmployee(String codeEmployee) {
        this.codeEmployee = codeEmployee;
    }

    public String getNameEmployee() {
        return nameEmployee;
    }

    public void setNameEmployee(String nameEmployee) {
        this.nameEmployee = nameEmployee;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public boolean isGenderEmployee() {
        return genderEmployee;
    }

    public void setGenderEmployee(boolean genderEmployee) {
        this.genderEmployee = genderEmployee;
    }

    public String getPhoneEmployee() {
        return phoneEmployee;
    }

    public void setPhoneEmployee(String phoneEmployee) {
        this.phoneEmployee = phoneEmployee;
    }

    public String getEmailEmployee() {
        return emailEmployee;
    }

    public void setEmailEmployee(String emailEmployee) {
        this.emailEmployee = emailEmployee;
    }

    public String getAddressEmployee() {
        return addressEmployee;
    }

    public void setAddressEmployee(String addressEmployee) {
        this.addressEmployee = addressEmployee;
    }

    public boolean isFlagDeleted() {
        return flagDeleted;
    }

    public void setFlagDeleted(boolean flagDeleted) {
        this.flagDeleted = flagDeleted;
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }


    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        EmployeeDto employeeDto = (EmployeeDto) target;

        String nameEmployee = employeeDto.getNameEmployee();
        String emailEmployee = employeeDto.getEmailEmployee();


        if (!nameEmployee.equals("")) {
            if (!nameEmployee.matches("^[A-Z][a-z]*(?: [A-Z][a-z]*)*$")) {
                errors.rejectValue("nameEmployee", "nameEmployee.matches", "error!");
            }
        }
        if (!emailEmployee.equals("")) {
            if (!emailEmployee.matches("^[a-zA-Z0-9]+[@]{1}[a-zA-Z0-9]+[.]{1}[a-zA-Z0-9]+$")) {
                errors.rejectValue("nameEmployee", "nameEmployee.matches", "error!");
            }
        }
    }
}
