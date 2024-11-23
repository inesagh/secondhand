package com.example.secondhandclothes.user.service;

import com.example.secondhandclothes.user.rest.models.UserRequestDto;
import com.example.secondhandclothes.user.rest.models.UserResponseDto;
import com.example.secondhandclothes.user.domain.User;
import com.example.secondhandclothes.util.exception.AppException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    UserResponseDto create(UserRequestDto userDAO);
    List<UserResponseDto> get();
    User getAuthenticatedUser() throws AppException;
}
