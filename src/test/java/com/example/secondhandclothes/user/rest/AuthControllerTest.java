package com.example.secondhandclothes.user.rest;

import com.example.secondhandclothes.user.rest.models.UserRequestDto;
import com.example.secondhandclothes.user.rest.models.AuthRequestDto;
import com.example.secondhandclothes.user.rest.models.UserResponseDto;
import com.example.secondhandclothes.user.service.UserService;
import com.example.secondhandclothes.infrastructure.security.JwtService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@Disabled
@WebMvcTest(AuthController.class)
public class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthController authController;

    private UserRequestDto userRequestDto;
    private AuthRequestDto authRequestDto;

    @Before
    public void setUp() {
        userRequestDto = new UserRequestDto();
        userRequestDto.setUsername("testuser");
        userRequestDto.setPassword("password");

        authRequestDto = new AuthRequestDto();
        authRequestDto.setUsername("testuser");
        authRequestDto.setPassword("password");
    }

    @Test
    public void createUserTest() throws Exception {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setUsername("testuser");

        when(userService.create(any(UserRequestDto.class))).thenReturn(userResponseDto);

        mockMvc.perform(post("/api/v1/auth/addUser")
                        .contentType("application/json")
                        .content("{\"username\":\"testuser\", \"password\":\"password\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("testuser"));
    }

    @Test
    public void authenticateAndGenerateTokenTest() throws Exception {
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(mock(Authentication.class));

        when(jwtService.generateToken("testuser")).thenReturn("new-jwt-token");

        mockMvc.perform(post("/api/v1/auth/generateToken")
                        .contentType("application/json")
                        .content("{\"username\":\"testuser\", \"password\":\"password\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("New token. new-jwt-token"));
    }

    @Test
    void authenticateAndGenerateTokenForInvalidUserTest() throws Exception {
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new RuntimeException("Authentication failed"));

        mockMvc.perform(post("/api/v1/auth/generateToken")
                        .contentType("application/json")
                        .content("{\"username\":\"wronguser\", \"password\":\"wrongpassword\"}"))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value("User doesn't exist. Please create and try again."));
    }

}
