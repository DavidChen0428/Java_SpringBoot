package com.david.project.constant;

public enum ErrorCode {

    E0000("Unknown error"),
    E0001("Invalid input"),
    E0002("Resource not found"),
    E0003("Unauthorized access");

    private String description;

    ErrorCode(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
