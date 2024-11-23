package com.example.secondhandclothes.exception;

import java.util.List;

public class GeneralResponse {
    private String message;
    private int statusCode;
    private List<String> details;

    public GeneralResponse(String message) {
        this.message = message;
    }

    public GeneralResponse(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public GeneralResponse(String message, int statusCode, List<String> details) {
        this.message = message;
        this.statusCode = statusCode;
        this.details = details;
    }

    public GeneralResponse(String message, int statusCode, Exception ex) {
        this.message = message;
        this.statusCode = statusCode;
        this.details = List.of(ex.getMessage());
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }
}
