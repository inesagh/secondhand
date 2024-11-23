package com.example.secondhandclothes.user.rest;

import com.example.secondhandclothes.user.rest.models.UserRequestDto;
import com.example.secondhandclothes.user.rest.models.UserResponseDto;
import com.example.secondhandclothes.user.rest.models.AuthRequestDto;
import com.example.secondhandclothes.util.exception.GeneralResponse;
import com.example.secondhandclothes.infrastructure.security.JwtService;
import com.example.secondhandclothes.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final UserService userService;
    private final JwtService jwtService;
    private AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(UserService userService, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/addUser")
    public ResponseEntity<UserResponseDto> create(@RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok(userService.create(userRequestDto));
    }

    @PostMapping("/generateToken")
    public ResponseEntity<GeneralResponse> authenticateAndGetToken(@RequestBody AuthRequestDto authRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequestDto.getUsername(), authRequestDto.getPassword())
        );

        if (authentication.isAuthenticated()) {
            return ResponseEntity.ok(new GeneralResponse(String.format("New token. %s",
                    jwtService.generateToken(authRequestDto.getUsername())), HttpStatus.OK.value()));
        } else {
            return ResponseEntity.ok(new GeneralResponse("User doesn't exist. Please create and try again.",
                    HttpStatus.FORBIDDEN.value()));
        }
    }
}
