package com.example.secondhandclothes.service;

import com.example.secondhandclothes.converter.Converter;
import com.example.secondhandclothes.controller.garment.models.GarmentRequestModel;
import com.example.secondhandclothes.controller.garment.models.GarmentResponseModel;
import com.example.secondhandclothes.entity.GarmentDAO;
import com.example.secondhandclothes.exception.AppException;
import com.example.secondhandclothes.repository.GarmentRepository;
import com.example.secondhandclothes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GarmentServiceImpl implements GarmentService{
    private final GarmentRepository garmentRepository;
    private final UserRepository userRepository;

    @Autowired
    public GarmentServiceImpl(GarmentRepository garmentRepository, UserRepository userRepository) {
        this.garmentRepository = garmentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public GarmentResponseModel create(GarmentRequestModel garmentRequestModel, Long publisherId) {
        GarmentDAO garmentToBeSaved = Converter.toDaoFromRequestModel(garmentRequestModel);
        garmentToBeSaved.setPublisher(userRepository.findById(publisherId).get());

        GarmentDAO savedGarment = garmentRepository.save(garmentToBeSaved);
        return Converter.toResponseModelFromDao(savedGarment);
    }

    @Override
    public GarmentResponseModel getById(Long id) {
        if(garmentRepository.findById(id).isEmpty()) {
            return null;
        }

        return Converter.toResponseModelFromDao(garmentRepository.findById(id).get());
    }


    @Override public List<GarmentResponseModel> search(String type, String description, String size, Double price,
            Long publisherId) {
        List<GarmentDAO> garmentList = garmentRepository.search(type, description, size, price, publisherId);
        return garmentList.stream().map(Converter::toResponseModelFromDao).toList();
    }

    @Override
    public List<GarmentResponseModel> get() {
        List<GarmentDAO> all = (List<GarmentDAO>) garmentRepository.findAll();
        return all.stream().map(Converter::toResponseModelFromDao).toList();
    }

    @Override
    public GarmentResponseModel update(GarmentRequestModel garmentRequestModel, Long id, Long publisherId)
            throws AppException {
        Optional<GarmentDAO> optionalExistingGarment = garmentRepository.findById(id);
        if(optionalExistingGarment.isEmpty()) {
            throw new AppException("Garment not found");
        }

        if(publisherId != optionalExistingGarment.get()
                .getPublisher().getId()){
            throw new AppException("You are not authorized to update this garment.");
        }

        GarmentDAO existingGarment = optionalExistingGarment.get();

        GarmentDAO garmentToBeUpdated = Converter.toDaoFromRequestModel(garmentRequestModel);
        if(garmentToBeUpdated.getType() != null && !garmentToBeUpdated.getType().isEmpty()) {
            existingGarment.setType(garmentToBeUpdated.getType());
        }

        if(garmentToBeUpdated.getDescription() != null && !garmentToBeUpdated.getDescription().isEmpty()) {
            existingGarment.setDescription(garmentToBeUpdated.getDescription());
        }

        if(garmentToBeUpdated.getSize() != null && !garmentToBeUpdated.getSize().isEmpty()) {
            existingGarment.setSize(garmentToBeUpdated.getSize());
        }

        if(garmentToBeUpdated.getPrice() != null && garmentToBeUpdated.getPrice() != 0) {
            existingGarment.setPrice(garmentToBeUpdated.getPrice());
        }

        return Converter.toResponseModelFromDao(garmentRepository.save(existingGarment));
    }

    @Override
    public boolean delete(Long garmentId, Long publisherId) throws AppException {
        Optional<GarmentDAO> optionalExistingGarment = garmentRepository.findById(garmentId);
        if(optionalExistingGarment.isPresent()) {
            GarmentDAO existingGarment = optionalExistingGarment.get();
            if (existingGarment.getPublisher().getId().equals(publisherId)) {
                garmentRepository.deleteById(garmentId);
                return true;
            }
        }
        throw new AppException("You are not authorized to unpublish this garment.");
    }
}
