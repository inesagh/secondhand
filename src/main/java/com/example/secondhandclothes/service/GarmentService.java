package com.example.secondhandclothes.service;


import com.example.secondhandclothes.controller.garment.models.GarmentRequestModel;
import com.example.secondhandclothes.controller.garment.models.GarmentResponseModel;
import com.example.secondhandclothes.entity.GarmentDAO;
import com.example.secondhandclothes.entity.UserDAO;
import com.example.secondhandclothes.exception.AppException;

import java.util.List;

public interface GarmentService {
    GarmentResponseModel create(GarmentRequestModel garmentRequestModel, Long publisherId);
    GarmentResponseModel getById(Long id);
    List<GarmentResponseModel> search(String type, String description, String size, Double price, Long publisherId);
    List<GarmentResponseModel> get();
    GarmentResponseModel update(GarmentRequestModel garmentRequestModel, Long id, Long publisherId) throws AppException;
    boolean delete(Long garmentId, Long publisherId) throws AppException;
}
