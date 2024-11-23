package com.example.secondhandclothes.util.converter;

import com.example.secondhandclothes.clothes.rest.models.GarmentRequestDto;
import com.example.secondhandclothes.clothes.rest.models.GarmentResponseDto;
import com.example.secondhandclothes.user.rest.models.UserRequestDto;
import com.example.secondhandclothes.user.rest.models.UserResponseDto;
import com.example.secondhandclothes.clothes.domain.Garment;
import com.example.secondhandclothes.user.domain.User;

public class Converter {
    public static Garment toEntityFromRequestDto(GarmentRequestDto requestModel) {
        Garment dao = new Garment();
        dao.setType(requestModel.getType());
        dao.setDescription(requestModel.getDescription());
        dao.setSize(requestModel.getSize());
        dao.setPrice(requestModel.getPrice());
        return dao;
    }

    public static GarmentResponseDto toResponseDtoFromEntity(Garment dao) {
        GarmentResponseDto responseModel = new GarmentResponseDto();
        responseModel.setId(dao.getId());
        responseModel.setType(dao.getType());
        responseModel.setDescription(dao.getDescription());
        responseModel.setSize(dao.getSize());
        responseModel.setPrice(dao.getPrice());
        responseModel.setPublisher(toResponseDtoFromEntity(dao.getPublisher()));
        return responseModel;
    }

    public static User toEntityFromRequestDto(UserRequestDto requestModel) {
        User dao = new User();
        dao.setFullName(requestModel.getFullName());
        dao.setAddress(requestModel.getAddress());
        dao.setUsername(requestModel.getUsername());
        dao.setPassword(requestModel.getPassword());
        dao.setRoles(requestModel.getRoles());
        return dao;
    }

    public static UserResponseDto toResponseDtoFromEntity(User dao) {
        UserResponseDto responseModel = new UserResponseDto();
        responseModel.setId(dao.getId());
        responseModel.setFullName(dao.getFullName());
        responseModel.setAddress(dao.getAddress());
        responseModel.setUsername(dao.getUsername());
        responseModel.setRoles(dao.getRoles());
        return responseModel;
    }
}
