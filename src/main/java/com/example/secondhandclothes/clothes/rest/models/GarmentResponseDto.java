package com.example.secondhandclothes.clothes.rest.models;

import com.example.secondhandclothes.user.rest.models.UserResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GarmentResponseDto {
    private Long id;
    private String type;
    private String description;
    private String size;
    private Double price;
    private UserResponseDto publisher;
}
