package com.codegym.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SignUpForm {
    @NotBlank
    @Size(min = 2, max = 50)
    String name;
    @NotBlank
    @Size(min = 2, max = 50)
    @Email
    String email;
    @NotBlank
    @Size(min = 6, max = 20)
    String password;
    String passwordConfirm;

    public SignUpForm() {
    }

    public SignUpForm(@NotBlank
                      @Size(min = 2, max = 50) String name,
                      @NotBlank
                      @Size(min = 2, max = 50)
                      @Email String email,
                      @NotBlank
                      @Size(min = 6, max = 20) String password,
                      String passwordConfirm
   ) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.passwordConfirm = passwordConfirm;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

}
