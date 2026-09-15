package com.example.trainingregistration.model;

import jakarta.validation.constraints.NotBlank;
import com.example.trainingregistration.Validation.Address;
import com.example.trainingregistration.Validation.GmailOnly;

public class Registration {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @GmailOnly
    private String email;

    @Address
    private String address;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}