package com.example.secondhandclothes.clothes.rest;

import com.example.secondhandclothes.clothes.rest.models.GarmentRequestDto;
import com.example.secondhandclothes.clothes.rest.models.GarmentResponseDto;
import com.example.secondhandclothes.clothes.service.GarmentService;
import com.example.secondhandclothes.user.domain.User;
import com.example.secondhandclothes.user.service.UserService;
import com.example.secondhandclothes.util.exception.AppException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled
@ExtendWith(MockitoExtension.class)
public class GarmentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private GarmentService garmentService;

    @Mock
    private UserService userService;

    @InjectMocks
    private GarmentController garmentController;

    private GarmentRequestDto garmentRequestDto;
    private GarmentResponseDto garmentResponseDto;

    @Before
    public void setUp() {
        garmentRequestDto = new GarmentRequestDto();
        garmentRequestDto.setType("Shirt");
        garmentRequestDto.setDescription("Cotton shirt");
        garmentRequestDto.setSize("M");
        garmentRequestDto.setPrice(20.0);

        garmentResponseDto = new GarmentResponseDto();
        garmentResponseDto.setId(1L);
        garmentResponseDto.setType("Shirt");
        garmentResponseDto.setDescription("Cotton shirt");
        garmentResponseDto.setSize("M");
        garmentResponseDto.setPrice(20.0);
    }

    @Test
    void getAllGarmentsTest() throws Exception {
        List<GarmentResponseDto> garments = Arrays.asList(garmentResponseDto);
        when(garmentService.search(any(), any(), any(), any(), any())).thenReturn(garments);

        mockMvc.perform(get("/api/v1/clothes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].type").value("Shirt"));
    }

    @Test
    void getGarmentByIdTest() throws Exception {
        when(garmentService.getById(1L)).thenReturn(garmentResponseDto);

        mockMvc.perform(get("/api/v1/clothes/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value("Shirt"));
    }

    @Test
    void publishGarmentTest() throws Exception {
        when(userService.getAuthenticatedUser()).thenReturn(mock(User.class)); // Mock user authentication
        when(garmentService.create(any(), any())).thenReturn(garmentResponseDto);

        mockMvc.perform(post("/api/v1/clothe")
                        .contentType("application/json")
                        .content("{\"type\":\"Shirt\",\"description\":\"Cotton shirt\",\"size\":\"M\",\"price\":20.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value("Shirt"));
    }

    @Test
    void updateGarmentTest() throws Exception {
        when(userService.getAuthenticatedUser()).thenReturn(mock(User.class)); // Mock user authentication
        when(garmentService.update(any(), any(), any())).thenReturn(garmentResponseDto);

        mockMvc.perform(put("/api/v1/clothe/{id}", 1L)
                        .contentType("application/json")
                        .content("{\"type\":\"Shirt\",\"description\":\"Cotton shirt updated\",\"size\":\"M\",\"price\":25.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value("Shirt"));
    }

    @Test
    void unpublishGarmentTest() throws Exception {
        when(userService.getAuthenticatedUser()).thenReturn(mock(User.class)); // Mock user authentication
        when(garmentService.delete(any(), any())).thenReturn(true);

        mockMvc.perform(delete("/api/v1/clothe/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Garment unpublished successfully."));
    }

    @Test
    void publishGarmentWithNoAuthenticationTest() throws Exception {
        when(userService.getAuthenticatedUser()).thenThrow(new AppException("Not authenticated user. Please authenticate first and try again."));

        mockMvc.perform(post("/api/v1/clothe")
                        .contentType("application/json")
                        .content("{\"type\":\"Shirt\",\"description\":\"Cotton shirt\",\"size\":\"M\",\"price\":20.0}"))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value("Not authenticated user. Please authenticate first and try again."));
    }

    @Test
    void updateGarmentWithNoAuthenticationTest() throws Exception {
        when(userService.getAuthenticatedUser()).thenThrow(new AppException("Not authenticated user. Please authenticate first and try again."));

        mockMvc.perform(put("/api/v1/clothe/{id}", 1L)
                        .contentType("application/json")
                        .content("{\"type\":\"Shirt\",\"description\":\"Cotton shirt updated\",\"size\":\"M\",\"price\":25.0}"))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value("Not authenticated user. Please authenticate first and try again."));
    }

}
