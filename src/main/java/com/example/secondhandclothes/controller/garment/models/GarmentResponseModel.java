package com.example.secondhandclothes.controller.garment.models;

import com.example.secondhandclothes.controller.user.models.UserResponseModel;

public class GarmentResponseModel {
    private Long id;
    private String type;
    private String description;
    private String size;
    private Double price;
    private UserResponseModel publisher;

    public GarmentResponseModel() {
    }

    public GarmentResponseModel(Long id, String type, String description, String size, Double price, UserResponseModel publisher) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.size = size;
        this.price = price;
        this.publisher = publisher;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public UserResponseModel getPublisher() {
        return publisher;
    }

    public void setPublisher(UserResponseModel publisher) {
        this.publisher = publisher;
    }
}
