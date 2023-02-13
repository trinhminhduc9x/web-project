package com.c0722g1repobe.dto.account.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SignInForm {
    @NotBlank
    @Size(min = 3, max = 50)
    private String username;

//    @JsonIgnore
    @NotBlank
    @Size(min = 6, max = 100)
    private String password;

    public SignInForm() {
    }

    public SignInForm(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
