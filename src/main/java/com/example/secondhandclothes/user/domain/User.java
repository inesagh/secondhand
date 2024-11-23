package com.example.secondhandclothes.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String address;
    @Column(unique = true)
    private String username;
    private String password;
    private String roles;

    public User() {
    }

    public User(Long id, String fullName, String address, String username, String password, String roles) {
        this.id = id;
        this.fullName = fullName;
        this.address = address;
        this.username = username;
        this.password = password;
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

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof User user))
            return false;

        if (!getId().equals(user.getId()))
            return false;
        if (getFullName() != null ? !getFullName().equals(user.getFullName()) : user.getFullName() != null)
            return false;
        if (getAddress() != null ? !getAddress().equals(user.getAddress()) : user.getAddress() != null)
            return false;
        if (!getUsername().equals(user.getUsername()))
            return false;
        if (!getPassword().equals(user.getPassword()))
            return false;
        return getRoles().equals(user.getRoles());
    }

    @Override public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + (getFullName() != null ? getFullName().hashCode() : 0);
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        result = 31 * result + getUsername().hashCode();
        result = 31 * result + getPassword().hashCode();
        result = 31 * result + getRoles().hashCode();
        return result;
    }

    @Override public String toString() {
        return "UserDAO{" + "id=" + id + ", fullName='" + fullName + '\'' + ", address='" + address + '\''
                + ", username='" + username + '\'' + ", password='" + password + '\'' + ", roles='" + roles + '\''
                + '}';
    }
}
