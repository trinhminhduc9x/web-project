package com.c0722g1repobe.dto.employee;

/**
 * Create by: NhanUQ
 * Date created: 31/01/2023
 * Interface: to get property show list employee
 */
public interface EmployeeInfo {
    Long getIdEmployee();

    String getCodeEmployee();

    String getNameEmployee();

    boolean isGenderEmployee();

    String getDateOfBirth();

    String getPhoneEmployee();

    String getEmailEmployee();

    String getNameDivision();
}
