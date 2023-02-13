package com.c0722g1repobe.dto.customer;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public class CustomerDto implements Validator {

    private Long idCustomer;

    private String nameCustomer;

    private String phoneCustomer1;

    private String dateOfBirth;

    private String phoneCustomer2;

    private String emailCustomer;

    private String addressCustomer;

    private String idCardCustomer;

    private String codeCustomer;

    private Integer genderCustomer;

    private boolean flagDelete = false;

    private int approvalCustomer;

    private String usernameAccount;

    private String encryptPassword;

    private String nameAccount;

    public String getNameAccount() {
        return nameAccount;
    }

    public void setNameAccount(String nameAccount) {
        this.nameAccount = nameAccount;
    }

    public String getUsernameAccount() {
        return usernameAccount;
    }

    public void setUsernameAccount(String usernameAccount) {
        this.usernameAccount = usernameAccount;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDateOfBirthCustomer() {
        return dateOfBirth;
    }

    public void setDateOfBirthCustomer(String dateOfBirthCustomer) {
        this.dateOfBirth = dateOfBirthCustomer;
    }

    public Long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public String getPhoneCustomer1() {
        return phoneCustomer1;
    }

    public void setPhoneCustomer1(String phoneCustomer1) {
        this.phoneCustomer1 = phoneCustomer1;
    }

    public String getPhoneCustomer2() {
        return phoneCustomer2;
    }

    public void setPhoneCustomer2(String phoneCustomer2) {
        this.phoneCustomer2 = phoneCustomer2;
    }

    public String getEmailCustomer() {
        return emailCustomer;
    }

    public void setEmailCustomer(String emailCustomer) {
        this.emailCustomer = emailCustomer;
    }

    public String getAddressCustomer() {
        return addressCustomer;
    }

    public void setAddressCustomer(String addressCustomer) {
        this.addressCustomer = addressCustomer;
    }

    public String getIdCardCustomer() {
        return idCardCustomer;
    }

    public void setIdCardCustomer(String idCardCustomer) {
        this.idCardCustomer = idCardCustomer;
    }

    public String getCodeCustomer() {
        return codeCustomer;
    }

    public void setCodeCustomer(String codeCustomer) {
        this.codeCustomer = codeCustomer;
    }

    public Integer getGenderCustomer() {
        return genderCustomer;
    }

    public void setGenderCustomer(Integer genderCustomer) {
        this.genderCustomer = genderCustomer;
    }

    public boolean isFlagDelete() {
        return flagDelete;
    }

    public void setFlagDelete(boolean flagDelete) {
        this.flagDelete = flagDelete;
    }

    public int getApprovalCustomer() {
        return approvalCustomer;
    }

    public void setApprovalCustomer(int approvalCustomer) {
        this.approvalCustomer = approvalCustomer;
    }

    public String getEncryptPassword() {
        return encryptPassword;
    }

    public void setEncryptPassword(String encryptPassword) {
        this.encryptPassword = encryptPassword;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
