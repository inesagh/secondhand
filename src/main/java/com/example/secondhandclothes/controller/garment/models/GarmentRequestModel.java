package com.example.secondhandclothes.controller.garment.models;

public class GarmentRequestModel {
    private String type;
    private String description;
    private String size;
    private Double price;

    public GarmentRequestModel() {
    }

    public GarmentRequestModel(String type, String description, String size, Double price) {
        this.type = type;
        this.description = description;
        this.size = size;
        this.price = price;
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
}
