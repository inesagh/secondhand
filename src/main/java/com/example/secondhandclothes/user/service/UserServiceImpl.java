package com.example.secondhandclothes.user.service;

import com.example.secondhandclothes.util.converter.Converter;
import com.example.secondhandclothes.user.rest.models.UserRequestDto;
import com.example.secondhandclothes.user.rest.models.UserResponseDto;
import com.example.secondhandclothes.user.domain.User;
import com.example.secondhandclothes.util.exception.AppException;
import com.example.secondhandclothes.user.domain.UserRepository;
import com.example.secondhandclothes.infrastructure.security.UserDAODetails;
import jakarta.transaction.Transactional;
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
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userDetail = userRepository.findByUsername(username); // Assuming 'email' is used as username

        return userDetail.map(UserDAODetails::new).orElseThrow(() ->
                new UsernameNotFoundException("User not found: " + username));
    }

    @Override public UserResponseDto create(UserRequestDto userRequestDto) {
        userRequestDto.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        User userToBeSaved = Converter.toEntityFromRequestDto(userRequestDto);

        User savedUser = userRepository.save(userToBeSaved);
        return Converter.toResponseDtoFromEntity(savedUser);
    }

    @Override public List<UserResponseDto> get() {
        List<User> all = (List<User>) userRepository.findAll();
        return all.stream().map(Converter::toResponseDtoFromEntity).toList();
    }

    @Override
    public User getAuthenticatedUser() throws AppException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException("User not found"));

    }
}
