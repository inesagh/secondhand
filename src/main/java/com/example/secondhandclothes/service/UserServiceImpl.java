package com.example.secondhandclothes.service;

import com.example.secondhandclothes.converter.Converter;
import com.example.secondhandclothes.controller.user.models.UserRequestModel;
import com.example.secondhandclothes.controller.user.models.UserResponseModel;
import com.example.secondhandclothes.entity.UserDAO;
import com.example.secondhandclothes.exception.AppException;
import com.example.secondhandclothes.repository.UserRepository;
import com.example.secondhandclothes.security.UserDAODetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserDAO> userDetail = userRepository.findByUsername(username); // Assuming 'email' is used as username

        return userDetail.map(UserDAODetails::new).orElseThrow(() ->
                new UsernameNotFoundException("User not found: " + username));
    }

    @Override public UserResponseModel create(UserRequestModel userRequestModel) {
        userRequestModel.setPassword(passwordEncoder.encode(userRequestModel.getPassword()));
        UserDAO userToBeSaved = Converter.toDaoFromRequestModel(userRequestModel);

        UserDAO savedUser = userRepository.save(userToBeSaved);
        return Converter.toResponseModelFromDao(savedUser);
    }

    @Override public List<UserResponseModel> get() {
        List<UserDAO> all = (List<UserDAO>) userRepository.findAll();
        return all.stream().map(Converter::toResponseModelFromDao).toList();
    }

    @Override
    public UserDAO getAuthenticatedUser() throws AppException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException("User not found"));

    }
}
