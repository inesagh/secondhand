package com.example.secondhandclothes.clothes.service;


import com.example.secondhandclothes.clothes.rest.models.GarmentRequestDto;
import com.example.secondhandclothes.clothes.rest.models.GarmentResponseDto;
import com.example.secondhandclothes.util.exception.AppException;

import java.util.List;

public interface GarmentService {
    GarmentResponseDto create(GarmentRequestDto garmentRequestDto, Long publisherId);
    GarmentResponseDto getById(Long id);
    List<GarmentResponseDto> search(String type, String description, String size, Double price, Long publisherId);
    List<GarmentResponseDto> get();
    GarmentResponseDto update(GarmentRequestDto garmentRequestDto, Long id, Long publisherId) throws AppException;
    boolean delete(Long garmentId, Long publisherId) throws AppException;
}
