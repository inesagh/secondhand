package com.example.secondhandclothes.user.rest.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequestDto {
    private String username;
    private String password;

    @Override public String toString() {
        return "AuthRequest{" + "username='" + username + '\'' + ", password='" + password + '\'' + '}';
    }
}
