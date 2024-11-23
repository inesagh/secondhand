package com.example.secondhandclothes.util.converter;

import com.example.secondhandclothes.clothes.rest.models.GarmentRequestDto;
import com.example.secondhandclothes.clothes.rest.models.GarmentResponseDto;
import com.example.secondhandclothes.user.rest.models.UserRequestDto;
import com.example.secondhandclothes.clothes.domain.Garment;
import com.example.secondhandclothes.user.domain.User;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Disabled
public class ConverterTest {

    @Test
    public void toDaoFromRequestModelTest() {
        GarmentRequestDto requestModel = new GarmentRequestDto();
        requestModel.setType("shoes");
        requestModel.setDescription("high heels");
        requestModel.setSize("35");
        requestModel.setPrice(100.10);

        Garment dao = Converter.toEntityFromRequestDto(requestModel);

        assertEquals(requestModel.getType(), dao.getType());
        assertEquals(requestModel.getDescription(), dao.getDescription());
        assertEquals(requestModel.getSize(), dao.getSize());
        assertEquals(requestModel.getPrice(), dao.getPrice());
    }

    @Test
    public void toResponseModelFromDaoTest() {
        Garment dao = new Garment();
        dao.setId(1L);
        dao.setType("shoes");
        dao.setDescription("high heels");
        dao.setSize("35");
        dao.setPrice(100.10);
        User publisherDAO = new User();
        publisherDAO.setId(1L);
        dao.setPublisher(publisherDAO);

        GarmentResponseDto responseModelFromDao = Converter.toResponseDtoFromEntity(dao);

        assertEquals(dao.getId(), responseModelFromDao.getId());
        assertEquals(dao.getType(), responseModelFromDao.getType());
        assertEquals(dao.getDescription(), responseModelFromDao.getDescription());
        assertEquals(dao.getSize(), responseModelFromDao.getSize());
        assertEquals(dao.getPrice(), responseModelFromDao.getPrice());
        assertEquals(dao.getPublisher().getId(), responseModelFromDao.getPublisher().getId());
    }

    @Test
    public void toUserDaoFromRequestModelTest() {
        UserRequestDto requestModel = new UserRequestDto();
        requestModel.setFullName("Asd Asdyna");
        requestModel.setAddress("Yerevan, Armenia");
        requestModel.setUsername("asd");
        requestModel.setPassword("random");
        requestModel.setRoles("ROLE_USER");

        User dao = Converter.toEntityFromRequestDto(requestModel);

        assertEquals(requestModel.getFullName(), dao.getFullName());
        assertEquals(requestModel.getAddress(), dao.getAddress());
        assertEquals(requestModel.getUsername(), dao.getUsername());
        assertEquals(requestModel.getPassword(), dao.getPassword());
        assertEquals(requestModel.getRoles(), dao.getRoles());
    }


}
