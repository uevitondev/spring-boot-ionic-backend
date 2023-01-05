package com.uevitondev.springweb.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;

public class EmailDTO implements Serializable {

    @NotEmpty(message = "Preenchimento Obrigatório!")
    @Email(message = "Email inválido!")
    private String email;

    public EmailDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
