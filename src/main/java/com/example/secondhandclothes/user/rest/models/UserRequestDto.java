package com.example.secondhandclothes.user.rest.models;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
    private String fullName;
    private String address;
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String roles;
}
