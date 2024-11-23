package com.example.secondhandclothes.controller.user.models;

public class UserResponseModel {
    private Long id;
    private String fullName;
    private String address;
    private String username;
    private String roles;

    public UserResponseModel() {
    }

    public UserResponseModel(Long id, String fullName, String address, String username, String roles) {
        this.id = id;
        this.fullName = fullName;
        this.address = address;
        this.username = username;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
