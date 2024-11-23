package com.example.secondhandclothes.controller.user;

import com.example.secondhandclothes.controller.user.models.UserRequestModel;
import com.example.secondhandclothes.controller.user.models.UserResponseModel;
import com.example.secondhandclothes.controller.user.models.AuthRequestModel;
import com.example.secondhandclothes.exception.GeneralResponse;
import com.example.secondhandclothes.service.JwtService;
import com.example.secondhandclothes.service.UserService;
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
@RequestMapping("/auth")
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
    public ResponseEntity<UserResponseModel> create(@RequestBody UserRequestModel userRequestModel) {
        return ResponseEntity.ok(userService.create(userRequestModel));
    }

    @PostMapping("/generateToken")
    public ResponseEntity<GeneralResponse> authenticateAndGetToken(@RequestBody AuthRequestModel authRequestModel) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequestModel.getUsername(), authRequestModel.getPassword())
        );

        if (authentication.isAuthenticated()) {
            return ResponseEntity.ok(new GeneralResponse(String.format("New token. %s",
                    jwtService.generateToken(authRequestModel.getUsername())), HttpStatus.OK.value()));
        } else {
            return ResponseEntity.ok(new GeneralResponse("User doesn't exist. Please create and try again.",
                    HttpStatus.FORBIDDEN.value()));
        }
    }
}
