package com.example.secondhandclothes.converter;

import com.example.secondhandclothes.controller.garment.models.GarmentRequestModel;
import com.example.secondhandclothes.controller.garment.models.GarmentResponseModel;
import com.example.secondhandclothes.controller.user.models.UserRequestModel;
import com.example.secondhandclothes.controller.user.models.UserResponseModel;
import com.example.secondhandclothes.entity.GarmentDAO;
import com.example.secondhandclothes.entity.UserDAO;

public class Converter {
    public static GarmentDAO toDaoFromRequestModel(GarmentRequestModel requestModel) {
        GarmentDAO dao = new GarmentDAO();
        dao.setType(requestModel.getType());
        dao.setDescription(requestModel.getDescription());
        dao.setSize(requestModel.getSize());
        dao.setPrice(requestModel.getPrice());
        return dao;
    }

    public static GarmentResponseModel toResponseModelFromDao(GarmentDAO dao) {
        GarmentResponseModel responseModel = new GarmentResponseModel();
        responseModel.setId(dao.getId());
        responseModel.setType(dao.getType());
        responseModel.setDescription(dao.getDescription());
        responseModel.setSize(dao.getSize());
        responseModel.setPrice(dao.getPrice());
        responseModel.setPublisher(toResponseModelFromDao(dao.getPublisher()));
        return responseModel;
    }

    public static UserDAO toDaoFromRequestModel(UserRequestModel requestModel) {
        UserDAO dao = new UserDAO();
        dao.setFullName(requestModel.getFullName());
        dao.setAddress(requestModel.getAddress());
        dao.setUsername(requestModel.getUsername());
        dao.setPassword(requestModel.getPassword());
        dao.setRoles(requestModel.getRoles());
        return dao;
    }

    public static UserResponseModel toResponseModelFromDao(UserDAO dao) {
        UserResponseModel responseModel = new UserResponseModel();
        responseModel.setId(dao.getId());
        responseModel.setFullName(dao.getFullName());
        responseModel.setAddress(dao.getAddress());
        responseModel.setUsername(dao.getUsername());
        responseModel.setRoles(dao.getRoles());
        return responseModel;
    }
}
