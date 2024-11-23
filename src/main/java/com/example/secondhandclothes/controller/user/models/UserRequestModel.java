package com.example.secondhandclothes.controller.user.models;

import jakarta.validation.constraints.NotNull;

public class UserRequestModel {
    private String fullName;
    private String address;
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String roles;

    public UserRequestModel() {
    }

    public UserRequestModel(String fullName, String address, String username, String password, String roles) {
        this.fullName = fullName;
        this.address = address;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
