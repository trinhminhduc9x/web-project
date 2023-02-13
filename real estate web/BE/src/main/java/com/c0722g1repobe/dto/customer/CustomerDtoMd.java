package com.c0722g1repobe.dto.customer;

import com.c0722g1repobe.entity.account.Account;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class CustomerDtoMd implements Validator {
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

    private Account account;
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }

    public CustomerDtoMd() {
    }

    public CustomerDtoMd(Long idCustomer, String nameCustomer, String phoneCustomer1, String dateOfBirth, String phoneCustomer2, String emailCustomer, String addressCustomer, String idCardCustomer, String codeCustomer, Integer genderCustomer, boolean flagDelete, int approvalCustomer, String usernameAccount, String encryptPassword, String nameAccount, Account account) {
        this.idCustomer = idCustomer;
        this.nameCustomer = nameCustomer;
        this.phoneCustomer1 = phoneCustomer1;
        this.dateOfBirth = dateOfBirth;
        this.phoneCustomer2 = phoneCustomer2;
        this.emailCustomer = emailCustomer;
        this.addressCustomer = addressCustomer;
        this.idCardCustomer = idCardCustomer;
        this.codeCustomer = codeCustomer;
        this.genderCustomer = genderCustomer;
        this.flagDelete = flagDelete;
        this.approvalCustomer = approvalCustomer;
        this.usernameAccount = usernameAccount;
        this.encryptPassword = encryptPassword;
        this.nameAccount = nameAccount;
        this.account = account;
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

    public String getUsernameAccount() {
        return usernameAccount;
    }

    public void setUsernameAccount(String usernameAccount) {
        this.usernameAccount = usernameAccount;
    }

    public String getEncryptPassword() {
        return encryptPassword;
    }

    public void setEncryptPassword(String encryptPassword) {
        this.encryptPassword = encryptPassword;
    }

    public String getNameAccount() {
        return nameAccount;
    }

    public void setNameAccount(String nameAccount) {
        this.nameAccount = nameAccount;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}
