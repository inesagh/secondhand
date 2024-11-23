package com.example.secondhandclothes.controller.user.models;

public class AuthRequestModel {
    private String username;
    private String password;

    public AuthRequestModel() {
    }

    public AuthRequestModel(String username, String password) {
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

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof AuthRequestModel that))
            return false;

        if (!getUsername().equals(that.getUsername()))
            return false;
        return getPassword().equals(that.getPassword());
    }

    @Override public int hashCode() {
        int result = getUsername().hashCode();
        result = 31 * result + getPassword().hashCode();
        return result;
    }

    @Override public String toString() {
        return "AuthRequest{" + "username='" + username + '\'' + ", password='" + password + '\'' + '}';
    }
}
