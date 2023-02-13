package com.c0722g1repobe.dto.account;

public class AccountDto {
    private Long idAccount;
    private String encryptPassword;
    private String newPassword;
    private String confirmPassword;

    public AccountDto() {
    }

    public AccountDto(Long idAccount, String encryptPassword, String newPassword, String confirmPassword) {
        this.idAccount = idAccount;
        this.encryptPassword = encryptPassword;
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
    }

    public Long getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Long idAccount) {
        this.idAccount = idAccount;
    }

    public String getEncryptPassword() {
        return encryptPassword;
    }

    public void setEncryptPassword(String encryptPassword) {
        this.encryptPassword = encryptPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}











