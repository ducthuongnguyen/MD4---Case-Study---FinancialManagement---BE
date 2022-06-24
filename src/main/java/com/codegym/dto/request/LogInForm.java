package com.codegym.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LogInForm {
    @NotBlank
    @Size(min = 6, max = 40)
    String email;
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    public LogInForm() {
    }

    public LogInForm(@NotBlank
                     @Size(min = 6, max = 40)
                     @Email String email,
                     @NotBlank
                     @Size(min = 6, max = 40) String password) {
        this.email = email;
        this.password = password;
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
}
