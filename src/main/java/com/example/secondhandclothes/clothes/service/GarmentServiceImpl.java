package com.example.secondhandclothes.clothes.service;

import com.example.secondhandclothes.util.converter.Converter;
import com.example.secondhandclothes.clothes.rest.models.GarmentRequestDto;
import com.example.secondhandclothes.clothes.rest.models.GarmentResponseDto;
import com.example.secondhandclothes.clothes.domain.Garment;
import com.example.secondhandclothes.util.exception.AppException;
import com.example.secondhandclothes.clothes.domain.GarmentRepository;
import com.example.secondhandclothes.user.domain.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GarmentServiceImpl implements GarmentService{
    private final GarmentRepository garmentRepository;
    private final UserRepository userRepository;

    @Autowired
    public GarmentServiceImpl(GarmentRepository garmentRepository, UserRepository userRepository) {
        this.garmentRepository = garmentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public GarmentResponseDto create(GarmentRequestDto garmentRequestDto, Long publisherId) {
        Garment garmentToBeSaved = Converter.toEntityFromRequestDto(garmentRequestDto);
        garmentToBeSaved.setPublisher(userRepository.findById(publisherId).get());

        Garment savedGarment = garmentRepository.save(garmentToBeSaved);
        return Converter.toResponseDtoFromEntity(savedGarment);
    }

    @Override
    public GarmentResponseDto getById(Long id) {
        if(garmentRepository.findById(id).isEmpty()) {
            return null;
        }

        return Converter.toResponseDtoFromEntity(garmentRepository.findById(id).get());
    }


    @Override public List<GarmentResponseDto> search(String type, String description, String size, Double price,
            Long publisherId) {
        List<Garment> garmentList = garmentRepository.search(type, description, size, price, publisherId);
        return garmentList.stream().map(Converter::toResponseDtoFromEntity).toList();
    }

    @Override
    public List<GarmentResponseDto> get() {
        List<Garment> all = (List<Garment>) garmentRepository.findAll();
        return all.stream().map(Converter::toResponseDtoFromEntity).toList();
    }

    @Override
    public GarmentResponseDto update(GarmentRequestDto garmentRequestDto, Long id, Long publisherId)
            throws AppException {
        Optional<Garment> optionalExistingGarment = garmentRepository.findById(id);
        if(optionalExistingGarment.isEmpty()) {
            throw new AppException("Garment not found");
        }

        if(!publisherId.equals(optionalExistingGarment.get().getPublisher().getId())){
            throw new AppException("You are not authorized to update this garment.");
        }

        Garment existingGarment = optionalExistingGarment.get();

        Garment garmentToBeUpdated = Converter.toEntityFromRequestDto(garmentRequestDto);
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

        return Converter.toResponseDtoFromEntity(garmentRepository.save(existingGarment));
    }

    @Override
    public boolean delete(Long garmentId, Long publisherId) throws AppException {
        Optional<Garment> optionalExistingGarment = garmentRepository.findById(garmentId);
        if(optionalExistingGarment.isPresent()) {
            Garment existingGarment = optionalExistingGarment.get();
            if (existingGarment.getPublisher().getId().equals(publisherId)) {
                garmentRepository.deleteById(garmentId);
                return true;
            }
        }
        throw new AppException("You are not authorized to unpublish this garment.");
    }
}
