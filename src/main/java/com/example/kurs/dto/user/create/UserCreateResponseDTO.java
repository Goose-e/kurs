package com.example.kurs.dto.user.create;

import lombok.Data;

@Data
public class UserCreateResponseDTO {
    private String message;

    public UserCreateResponseDTO(String message) {
        this.message = message;
    }
    public UserCreateResponseDTO( ) {

    }
}
