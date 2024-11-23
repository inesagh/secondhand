package com.example.secondhandclothes.clothes.rest.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GarmentRequestDto {
    private String type;
    private String description;
    private String size;
    private Double price;
}
