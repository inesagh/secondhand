package com.example.secondhandclothes.service;

import com.example.secondhandclothes.controller.user.models.UserRequestModel;
import com.example.secondhandclothes.controller.user.models.UserResponseModel;
import com.example.secondhandclothes.entity.UserDAO;
import com.example.secondhandclothes.exception.AppException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    UserResponseModel create(UserRequestModel userDAO);
    List<UserResponseModel> get();
    UserDAO getAuthenticatedUser() throws AppException;
}
